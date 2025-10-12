package com.gxt.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.gxt.pojo.Result;
import com.gxt.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component //交给IOC容器管理
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override //目标资源方法运行前运行,返回true:放行,返回false,不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的URL: {}",url);

        //2.判断请求url中是否包含login,如果包含,说明是登录,直接放行
        if(url.contains("login")){
            log.info("登录操作,放行...");
            return true;
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
            return false;
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
            return false;

        }
        log.info(jwt);
        //6.令牌合法,放行
        log.info("成功");
        return true;
    }

    @Override //目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle .....");
    }

    @Override //视图渲染完毕后运行,最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion .....");
    }
}
