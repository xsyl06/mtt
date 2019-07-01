package com.ww.mtt.util;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    paramError("1001", "参数错误");

    private String errCode;
    private String errMsg;

    ErrorEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * 根据val获取枚举值
     * @param code
     * @return
     */
    public static ErrorEnum getValue(String code) {

        for (ErrorEnum errorEnum : values()) {
            if (errorEnum.getErrCode().equals(code)) {
                return errorEnum;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ErrorEnum e = ErrorEnum.getValue("1001");
        System.out.println(e.getErrMsg());
    }
}