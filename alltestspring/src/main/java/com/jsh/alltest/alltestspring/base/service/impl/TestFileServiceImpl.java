package com.jsh.alltest.alltestspring.base.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsh.alltest.alltestspring.base.model.TestFile;
import com.jsh.alltest.alltestspring.base.service.TestFileService;

@Service
public class TestFileServiceImpl implements TestFileService {

    @Value("${file.path}")
    private String FILE_PATH; // 저장할 경로

    @Override
    public List<TestFile> addFile(MultipartFile[] files) {
        List<TestFile> fileList = new ArrayList<>();
        try {
            fileList = fileProcess(files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    @Override
    public TestFile addFile(MultipartFile file) {
        TestFile returnFile = null;
        MultipartFile[] files = {file};
        try {
            List<TestFile> fileList = fileProcess(files);
            if (!fileList.isEmpty()) {
                returnFile = fileList.get(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnFile;
    }

    private List<TestFile> fileProcess(MultipartFile[] files) throws IOException{
        ArrayList<TestFile> fileList = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileNm = file.getOriginalFilename();
            System.out.println("check file nm : " + fileNm + " / size : " + file.getSize());

            String fullPath = FILE_PATH + "/" + fileNm;
            File writeFile = new File(fullPath);

            if(file.getSize() != 0) {
                if(!writeFile.exists()) { // 파일 존제 확인 및 생성
                    if(writeFile.getParentFile().mkdir()) {
                        writeFile.createNewFile();
                    }
                }

                file.transferTo(new File(fullPath));

                TestFile fileData = TestFile.builder()
                                        .name(fileNm)
                                        .size(file.getSize())
                                        .path(fullPath)
                                        .build();
                fileList.add(fileData);
            }
        }

        return fileList;
    }

    @Override
    public TestFile getFile(String name) {
        String fullPath = FILE_PATH + "/" + name;
        File file = new File(fullPath);

        if (file.exists()) {
            TestFile fileData = TestFile.builder()
                                        .name(file.getName())
                                        .size(file.length())
                                        .path(fullPath)
                                        .build();
            return fileData;
        }
        return null;
    }

}
