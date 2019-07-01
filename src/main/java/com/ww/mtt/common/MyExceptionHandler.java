package com.ww.mtt.common;

import com.ww.mtt.exception.OwnExcetpein;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    //@ExceptionHandler指定要捕捉处理的异常类型
    //各层的业务逻辑代码遇到异常直接往上层抛出，最终将会在这个类进行统一处理
    @ExceptionHandler(value = OwnExcetpein.class)
    @ResponseBody
    public Object errorHandler(OwnExcetpein e) {
        logger.info(e.toString());
        return JsonData.fail(e.getMessage(),e.getErrorCode());
    }
}
