package com.jsh.alltest.alltestspring.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsh.alltest.alltestspring.base.model.BaseRestModel;
import com.jsh.alltest.alltestspring.base.model.TestUser;
import com.jsh.alltest.alltestspring.base.model.TestUserDAO;

/**
 * service
 * business(domain) logic layer. service layer
 * 비즈니스 로직 수행
 * 
 * 트랜잭션 단위
 * API에서 발생하는 트랙잭션과 Domain의 순서 보장의 역할
 * 비즈니스 로직 처리와 비즈니스와 관련된 도메인 모델의 적합성을 검증하고, 트랜잭션을 처리
 * 
 * POJO(Plain Old Java Object)
 * 객체지향 충실. 필요에 따라 재활용
 */
@Service
public class BaseRestService {
    
    @Autowired
    private BaseRestModel baseRestModel;

    @Autowired
    private TestUserDAO testUserDAO;

    public String getServerType() {
        return baseRestModel.getServerType();
    }

    public BaseRestModel getBaseRestData() {
        return baseRestModel;
    }

    public void addUser(TestUser user) {
        testUserDAO.addUser(user);
    }

    public TestUser getUser(int no) {
        return testUserDAO.getUser(no);
    }
}
