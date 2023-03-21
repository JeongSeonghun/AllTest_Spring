package com.jsh.alltest.alltestspring.base.model;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

/**
 * DAO(Database Access Object)
 * persistence(data) layer
 * 데이터베이스 접근 객체
 * Mapper를 사용하기도 함.
 * 
 * jpa 인터페이스를 활용 CRUD
 * 
 * 오직 1개의 Connection으로 다수의 요청을 모두 수행하기 때문에 WA의 안정된 운영을 보장
 * 
 * 로직 Test용(실제 DB 연동x)
 * 
 */
@Repository
@AllArgsConstructor
public class TestUserDAO {
    private ArrayList<TestUser> users = new ArrayList<>();

    @PostConstruct
    void init() {
        Random random = new Random();
        for(int no = 0 ; no < 10 ; no++) {
            TestUser user = TestUser.builder()
                                .no(no)
                                .name("user"+no)
                                .age(random.nextInt(20) + 10)
                                .build();
            users.add(user);
        }
    }

    public void addUser(TestUser user) {
        user.setNo(users.size() + 1);
        users.add(user);
    }

    public TestUser getUser(int no) {
        TestUser testUser = null;
        for(TestUser user : users) {
            if (user.getNo() == no) {
                testUser = user;
                break;
            }
        }
        return testUser;
    }

}
