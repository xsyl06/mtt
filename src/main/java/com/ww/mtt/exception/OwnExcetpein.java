package com.ww.mtt.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OwnExcetpein extends RuntimeException {
    private String errorCode;
    private String message;

    public OwnExcetpein(String code, String msg) {
        this.message = msg;
        this.errorCode = code;
    }
}
