package com.example.demo.log.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class LogToStringUtil {
    public static String objectToString(Object object) {
        String s = JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue);
        return s;
    }
}
