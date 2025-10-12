package com.gxt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//请求处理类 加上注解才能标识为请求处理类
@RestController
public class HelloController {
    //这个注解代表浏览器处理/hello地址就会调用下面的方法
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("Hello");
        return "Hello";
    }
}
