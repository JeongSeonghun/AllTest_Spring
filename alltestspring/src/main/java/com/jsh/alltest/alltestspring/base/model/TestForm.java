package com.jsh.alltest.alltestspring.base.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestForm {
    // get, set 필수 
	private String name;
	private MultipartFile[] files;
}
