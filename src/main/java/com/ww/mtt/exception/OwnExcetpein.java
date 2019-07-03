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

    public OwnExcetpein() {
        super();
    }

    public OwnExcetpein(String message) {
        super(message);
        this.message = message;
        this.errorCode = "-999";
    }

    public OwnExcetpein(String message, Throwable cause) {
        super(message, cause);
    }

    public OwnExcetpein(Throwable cause) {
        super(cause);
    }

    protected OwnExcetpein(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
