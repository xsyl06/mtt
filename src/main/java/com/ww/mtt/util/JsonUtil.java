package com.ww.mtt.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    /**
     * 对象转换为字符串
     * @param src
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T src) {
        if (src == null) {
            return null;
        }
        try {
            return src instanceof String ? (String)src : objectMapper.writeValueAsString(src);
        } catch (Exception e) {
            logger.error("parse object to String exception, error:{}",e);
            return null;
        }
    }

    /**
     * 字符串转对象
     * @param src 字符串
     * @param typeReference  new TypeReference<Map<String,Objcet>>(){}
     * @param <T> 要转换的对象类型
     * @return
     */
    public static <T> T string2Object(String src, TypeReference<T> typeReference) {
        if(src == null || typeReference == null){
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src, typeReference));
        } catch (Exception e) {
            logger.error("parse String to Object exception, String:{}, TyeReference:{}, error:{}",src,typeReference.getType(),e);
            return null;
        }
    }
}
