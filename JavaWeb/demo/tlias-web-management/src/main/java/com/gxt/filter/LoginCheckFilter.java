package com.gxt.filter;

import com.alibaba.fastjson.JSONObject;
import com.gxt.pojo.Result;
import com.gxt.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的URL: {}",url);

        //2.判断请求url中是否包含login,如果包含,说明是登录,直接放行
        if(url.contains("login")){
            log.info("登录操作,放行...");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3.获取请求头的令牌 (token)
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在,如果不存在报错
        if(!StringUtils.hasLength(jwt)){
            log.info("请求为空,返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换成json
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);
            return;
        }
        //5.解析token,如果解析失败,返回error
        try {
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){ //解析失败
            e.printStackTrace();
            log.info("解析令牌失败");
            Result error = Result.error("NOT_LOGIN");
            //手动转换成json
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);
            return;

        }
        log.info(jwt);
        //6.令牌合法,放行
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("成功");

    }
}
