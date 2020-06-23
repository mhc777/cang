package com.java.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JSONUtils {


    public static String getJson(Object object)
    {
        return getJson(object,"yyyy-MM-dd HH:mm:ss");
    }

    public static String getJson(Object object, String dateFormat){

        ObjectMapper mapper = new ObjectMapper();

        //让mapper不返回时间戳，关闭其时间戳功能
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);

        //格式化时间戳
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        //为mapper指定时间格式
        mapper.setDateFormat(sdf);


        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

}
