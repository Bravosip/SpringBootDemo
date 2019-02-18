package com.example.demo.log.util;

import org.apache.log4j.MDC;

public class MDCUtil {
    /**日志上下文中，常量字符串输出KEY值*/
    public static final String MDC_STRING_KEY = "demoMDC";

    /**日志上下文中，常量保存KEY值*/
    public static final String MDC_DATA_KEY = "demoMDCDatas";

    /**取得常量字符串*/
    public static String get(){
        return (String) MDC.get(MDC_STRING_KEY);
    }

    
}
