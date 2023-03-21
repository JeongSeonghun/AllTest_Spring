package com.jsh.alltest.alltestspring.base.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class TestFile {
    private String name;
    private long size;
    private String path;
}
