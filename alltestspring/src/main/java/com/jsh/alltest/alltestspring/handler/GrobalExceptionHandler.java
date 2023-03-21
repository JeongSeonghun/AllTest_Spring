package com.jsh.alltest.alltestspring.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsh.alltest.alltestspring.conf.ErrorCode;
import com.jsh.alltest.alltestspring.exception.BaseError;
import com.jsh.alltest.alltestspring.exception.CusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GrobalExceptionHandler {
    
    @ExceptionHandler(CusException.class)
    protected ResponseEntity handleCusException(CusException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        BaseError error = new BaseError(errorCode.getStatus(), errorCode.getMsg());
        log.error("check error : {}", exception.getErrorCode().getMsg());
        
        return new ResponseEntity(error, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
}
