package com.jsh.alltest.alltestspring.exception;

import com.jsh.alltest.alltestspring.conf.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CusException extends RuntimeException {
    private final ErrorCode errorCode;
}
