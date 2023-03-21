package com.jsh.alltest.alltestspring.base.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsh.alltest.alltestspring.base.model.TestFile;
import com.jsh.alltest.alltestspring.base.service.TestFileService;

/**
 * file download test
 * FileUtil -> commons-io 사용
 */
@Controller
public class TestDownloadController {

    @Autowired
    private TestFileService testFileService;
    
    @RequestMapping("testdownload")
    public void download(@RequestParam String name, HttpServletResponse response) throws IOException {
        System.out.println("request download name : " + name);
        TestFile fileData = testFileService.getFile(name);

        if (fileData != null) {
            String originalFileName = fileData.getName();
            File file = new File(fileData.getPath());
            byte fileByte[] = FileUtils.readFileToByteArray(file); // commons-io library 사용

            response.setContentType("application/octet-stream");
		    response.setContentLength(fileByte.length);
		    response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		    response.getOutputStream().write(fileByte);
		    response.getOutputStream().flush();
		    response.getOutputStream().close();
        }
    }

}
