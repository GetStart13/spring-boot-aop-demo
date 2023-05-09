package com.springboot.framework.demo;

import com.springboot.framework.demo.controller.AopController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest//(classes = Test.class)
class DemoApplicationTests {

    private AopController aopController;

    @Autowired
    public void setBeanController(AopController aopController) {
        this.aopController = aopController;
    }

    @Test
    void contextLoads() {
        AopController mock = Mockito.mock(AopController.class);
        System.out.println(mock);
//        System.out.println(beanController);
    }

    @Test
    void aopTest() {
        String result = aopController.result();
        System.out.println(result);
    }

}
