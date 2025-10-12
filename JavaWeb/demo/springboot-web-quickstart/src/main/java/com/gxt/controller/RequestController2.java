package com.gxt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RequestController2 {
    //简单方法
    @RequestMapping("/simpleParam2")
    public String simpleParam(String name,Integer age){
        System.out.println(name + ":" + age);
        return "OK";
    }
}
