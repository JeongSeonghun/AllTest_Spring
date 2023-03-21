package com.jsh.alltest.alltestspring.base.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO(Data transfer object)
 * 게층간 데이터 교환 객체
 * 
 * builder 패턴 적용(Lombok builder 사용으로 간략화)
 * post body로 받기위해 기본 생성자 필요 
 * -> public TestUser()
 * -> class가 아닌 생성자에 @Builder 추가
 * 
 * test용으로 setter 추가
 * 
 */
@Setter
@Getter
@ToString
public class TestUser {
    private Integer no;
    private String name;
    private int age;

    @Builder
    public TestUser(Integer no, String name, int age) {
        this.no = no;
        this.name = name;
        this.age = age;
    }

    public TestUser() {
        this.age = -1;
    }
}
