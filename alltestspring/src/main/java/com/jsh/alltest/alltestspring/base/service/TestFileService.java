package com.jsh.alltest.alltestspring.base.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jsh.alltest.alltestspring.base.model.TestFile;

public interface TestFileService {
    List<TestFile> addFile(MultipartFile[] files);
    TestFile addFile(MultipartFile file);
    TestFile getFile(String name);
}
