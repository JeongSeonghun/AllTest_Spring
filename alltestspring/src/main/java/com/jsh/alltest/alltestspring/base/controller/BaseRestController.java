package com.jsh.alltest.alltestspring.base.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsh.alltest.alltestspring.base.model.TestFile;
import com.jsh.alltest.alltestspring.base.model.TestForm;
import com.jsh.alltest.alltestspring.base.model.TestUser;
import com.jsh.alltest.alltestspring.base.service.BaseRestService;
import com.jsh.alltest.alltestspring.base.service.TestFileService;
import com.jsh.alltest.alltestspring.conf.ErrorCode;
import com.jsh.alltest.alltestspring.exception.CusException;

import lombok.extern.slf4j.Slf4j;

/**
 * RestController
 * presentation layer
 * JSON/XML 형태 객체 데이터 반환 목적
 */
@RestController
@RequestMapping("/base")
@Slf4j // logger 초기화 코드 간소화 (private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExample.class); )
public class BaseRestController {

    @Autowired
    private BaseRestService service;

    @Autowired
	private TestFileService testFileService;

    /**
     * server 확인
     * @return
     */
    @GetMapping("/check")
    public String checkServer() {
        log.debug("checkServer request");
        return "server ok";
    }

    /**
     * properties 확인
     * @return
     */
    @GetMapping("/check/property")
    public Object checkProperty() {
        log.debug("checkProperty request");
        return service.getBaseRestData();
    }

     /**
     * ObjectMapper test
     * @param body
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @PostMapping("/getData")
	public Object getTestData(@RequestBody String body) throws JsonMappingException, JsonProcessingException {
		System.out.println("request ObjectMapper test api");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(body, new TypeReference<Map<String,Object>>(){});
		Map<String, Object> userRequest = ((Map<String,Object>) map.get("userRequest"));
		return userRequest.get("testData");
	}

    @GetMapping("/testUser")
	public Object getTestUser(int no) {
		// service test
		System.out.println("request get test user");

		TestUser user = service.getUser(no);

		System.out.println("check user : "+user);
		return user;
	}

	@PostMapping("/testUser")
	public String addTestUser(@RequestBody TestUser user) {
		// service test
		System.out.println("request add test user");

		service.addUser(user);

		return "success";
	}

    /**
     * log4j 확인
     * @return
     */
    @GetMapping("/checkLog")
	public String checkLog() {
		// level TRACE > DEBUG > INFO > WARN > ERROR
		log.trace("log test {} log", "TRACE");
        log.debug("log test {} log", "DEBUG");
        log.info("log test {} log", "INFO");
        log.warn("log test {} log", "WARN");
        log.error("log test {} log", "ERROR");
		return "check";
	}

    /**
     * GlobalException 확인
     * @return
     */
    @GetMapping("/checkException")
	public String checkException() {
		int remain = 0;
		try{
			remain = 100/0;
		}catch(ArithmeticException ex) {
			throw new CusException(ErrorCode.INVALID_PARAMETER);
		}
		log.info("check api exception : {}", remain);
		return "check ex";
	}
 
    /**
     * mutipart test
     * @param name
     * @param file
     * @return
     */
    @PostMapping("/testMultipart")
	public String testMultipart(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
		// multipart test
		System.out.println("request multipart test name : " + name);
		if (file != null && !file.isEmpty()) {
			TestFile testFile = testFileService.addFile(file);
			if (testFile != null) {
				return "success";
			}
		}
		return "fail";
	}
    /**
     * mutipart test
     * @param form
     * @param errors
     * @return
     */
	@PostMapping("/testMultipartForm")
	public String testMultipartForm(TestForm form, BindingResult errors) {
		// multipart binding test. multiple file
		System.out.println("request multipart form test name : " + form.getName());
		if (form.getFiles() != null && form.getFiles().length > 0) {
			List<TestFile> files = testFileService.addFile(form.getFiles());
			if (!files.isEmpty()) {
				return "success";
			}
		}
		
		return "fail";
	}
}
