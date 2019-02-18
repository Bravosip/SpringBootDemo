package com.example.demo.log.util;

/**
 * 这个类的调用简化了一下，实际使用中还是应该根据机器节点和数据中心节点来配置相关的参数。
 * 这里假设只有一个节点作为ID号的生成器，所以workerId和datacenterId都设为0，
 * 当前时间与计算标记时间twepoch（Thu, 04 Nov 2010 01:42:54 GMT）之间的毫秒数是一个38位长度的long值，再左移timestampLeftShift（22位），
 * 就得到一个60位长度的long数字，该数字与datacenterId << datacenterIdShift取或，datacenterId最小值为0，最大值为31，所以长度为1-5位，
 * datacenterIdShift是17位，所以结果就是最小值为0，最大值为22位长度的long，同理，workerId << workerIdShift的最大值为17位的long。所以最终生成的会是一个64位的long型唯一ID
 */

/**
 * 全局唯一id生成工具类
 * @author WangHao
 * @since 2018-08-21
 */
public class IdGenUtils {
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long twepoch = 1288834974657L;                              //  Thu, 04 Nov 2010 01:42:54 GMT 标记时间 用来计算偏移量，距离当前时间不同，得到的数据的位数也不同
    private long workerIdBits = 5L;                                     //  物理节点ID长度
    private long datacenterIdBits = 5L;                                 //  数据中心ID长度
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);             //  最大支持机器节点数0~31，一共32个
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);     //  最大支持数据中心节点数0~31，一共32个
    private long sequenceBits = 12L;                                    //  序列号12位， 4095，同毫秒内生成不同id的最大个数
    private long workerIdShift = sequenceBits;                          //  机器节点左移12位
    private long datacenterIdShift = sequenceBits + workerIdBits;       //  数据中心节点左移17位
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; //  时间毫秒数左移22位
    private long sequenceMask = -1L ^ (-1L << sequenceBits);                          // 用于和当前时间戳做比较，以获取最新时间
    private long lastTimestamp = -1L;


    //成员类，IdGenUtils的实例对象的保存域
    private static class IdGenHolder {
        private static final IdGenUtils instance = new IdGenUtils();
    }
    //外部调用获取IdGenUtils的实例对象，确保不可变
    public static IdGenUtils get(){
        return IdGenHolder.instance;
    }
    //初始化构造，无参构造有参函数，默认节点都是0
    public IdGenUtils() {
        this(0L, 0L);
    }
    //设置机器节点和数据中心节点数，都是 0-31
    public IdGenUtils(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    //线程安全的id生成方法
    @SuppressWarnings("all")
    public synchronized long nextId() {
        //获取当前毫秒数
        long timestamp = timeGen();
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                //自旋等待到下一毫秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加，每个毫秒时间内，都是从0开始计数，最大4095
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        // 最后按照规则拼出ID 64位
        // 000000000000000000000000000000000000000000  00000            00000       000000000000
        //1位固定整数   time                                       datacenterId   workerId    sequence
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    //比较当前时间和过去时间，防止时钟回退（机器问题），保证给的都是最新时间/最大时间
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    //获取当前的时间戳（毫秒）
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 获取带自定义前缀的全局唯一编码
     */
    public String getStrCodingByPrefix(String prefix){
        Long ele = this.nextId();
        return prefix + ele.toString();
    }

    public static void main(String[] args) {
        String s = IdGenUtils.get().getStrCodingByPrefix("");
        System.out.println(s);
    }
}
