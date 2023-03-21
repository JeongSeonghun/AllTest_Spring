package com.jsh.alltest.alltestspring.conf;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_PARAMETER(400, "check parameter");
    
    private final int status;
    private final String msg;
}
