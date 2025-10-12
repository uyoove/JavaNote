package com.gxt.aop;

import com.alibaba.fastjson.JSONObject;
import com.gxt.mapper.OperateLogMapper;
import com.gxt.pojo.OperateLog;
import com.gxt.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Slf4j
@Aspect //切面类
public class LogAspect {

    @Autowired
    private HttpServletRequest request; //拿到请求

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.gxt.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人ID - 当前登录员工ID -> 获取请求头的JWT令牌,解析令牌
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        log.info("{}",claims);
        Integer OperateUserId = (Integer) claims.get("id");

        //当前时间
        LocalDateTime now = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //方法返回值
        //调用原始目标方法运行
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed(args);
        long end = System.currentTimeMillis();
        String resultValue = JSONObject.toJSONString(result);

        //操作耗时
        long costTime = end - begin;

        //记录操作日志
        OperateLog operateLog = new OperateLog(
                null,
                OperateUserId,
                now,
                className,
                methodName,
                methodParams,
                resultValue,
                costTime);
        operateLogMapper.insert(operateLog);

        log.info("日志: {}",operateLog);
        return result;
    }
}
