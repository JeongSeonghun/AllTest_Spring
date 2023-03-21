package com.jsh.alltest.alltestspring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jsh.alltest.alltestspring.base.model.BaseRestModel;

@SpringBootTest
@ActiveProfiles("local")
public class SpringPropertyTest {
    
    @Autowired
    BaseRestModel checkProperty;

    @Test
    void checkProperty() {
        System.out.println("check property : "+ checkProperty);
    }
}
