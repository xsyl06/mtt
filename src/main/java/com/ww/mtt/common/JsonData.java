package com.ww.mtt.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class JsonData {

    private boolean ret;
    private String rtnCode;
    private String msg;
    private Object data;

    private static final String SUCCESS = "0";

    public JsonData(boolean ret) {
        this.ret = ret;
    }

    /**
     * 调用成功需返回信息时调用
     * @param object
     * @param msg
     * @return
     */
    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.setRtnCode(SUCCESS);
        jsonData.setData(object);
        jsonData.setMsg(msg);
        return jsonData;
    }
    /**
     * 调用成功需返回信息时调用
     * @param object
     * @return
     */
    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(true);
        jsonData.setRtnCode(SUCCESS);
        jsonData.setData(object);
        return jsonData;
    }
    /**
     * 调用成功不需返回信息时调用
     * @return
     */
    public static JsonData success() {
        JsonData jsonData = new JsonData(true);
        jsonData.setRtnCode(SUCCESS);
        return jsonData;
    }

    /**
     * 失败时调用
     * @param msg
     * @return
     */
    public static JsonData fail(String msg,String rtnCode) {
        JsonData jsonData = new JsonData(false);
        jsonData.setRtnCode(rtnCode);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("rtnCode", rtnCode);
        result.put("data", data);
        return result;
    }




}
