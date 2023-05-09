package com.springboot.framework.demo.service;

import org.springframework.stereotype.Service;

@Service
public class AopService {
    public String result() {
        return "this is service";
    }
}
