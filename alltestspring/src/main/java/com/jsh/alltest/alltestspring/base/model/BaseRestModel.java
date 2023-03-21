package com.jsh.alltest.alltestspring.base.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class BaseRestModel {

    @Value("${application.type}")
    private String serverType;

    @Value("${base.fixVal}")
    private String fixVal;

}
