package com.springboot.framework.demo.controller;

import com.springboot.framework.demo.service.AopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop/test")
public class AopController {
    @Autowired
    private AopService aopService;

    @GetMapping("/result")
    public String result() {
        return aopService.result();
    }

    @GetMapping("/args")
    public String args(String param) {
        return param;
    }
}
