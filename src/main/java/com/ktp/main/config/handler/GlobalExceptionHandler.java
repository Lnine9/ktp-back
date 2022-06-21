package com.ktp.main.config.handler;


import com.ktp.main.config.exception.TransException;
import com.ktp.main.util.ResResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@ControllerAdvice // 处理全局数据
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResResult<Object> handleException(Exception e) {
        LOG.error(Arrays.toString(e.getStackTrace()), e);
        return ResResult.fail("服务器异常");
    }

    @ExceptionHandler(TransException.class)
    @ResponseBody
    public ResResult<Object> handleOpdRuntimeException(TransException e) {
        return ResResult.fail(e.getExceptionCode().code, e.getExceptionCode().msg);
    }
}
