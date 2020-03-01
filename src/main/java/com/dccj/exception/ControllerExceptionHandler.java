package com.dccj.exception;

import com.dccj.controller.RespEntity;
import com.dccj.uitl.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespEntity handleException(Exception e) {
        log.error(e.getMessage(), e);
        if (e.getMessage().indexOf("PK_") >= 0)
            return new RespEntity(RespEntity.CODE_ERROR_IDX);
        else if (e instanceof AppException)
            return new RespEntity(RespEntity.CODE_ERROR, e.getMessage());
        else
            return new RespEntity(RespEntity.CODE_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public RespEntity handleException(RuntimeException e) {
        log.error(e.getMessage(), e);
        if (e.getMessage().indexOf("PK_") >= 0)
            return new RespEntity(RespEntity.CODE_ERROR_IDX);
        else if (e instanceof AppException)
            return new RespEntity(RespEntity.CODE_ERROR, e.getMessage());
        else
            return new RespEntity(RespEntity.CODE_ERROR);
    }
}








