package com.gxt.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    @Override       //初始化,只会调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 初始方法");

    }

    @Override //拦截请求之后调用,调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到请求.....放行前逻辑");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("方形后逻辑");
    }

    @Override //销毁,只会调用一次
    public void destroy() {
        System.out.println("销毁了");
    }
}
