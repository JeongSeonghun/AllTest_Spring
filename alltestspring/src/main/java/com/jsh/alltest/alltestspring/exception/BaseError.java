package com.jsh.alltest.alltestspring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseError {
    private final int code;
    private final String msg;
}
