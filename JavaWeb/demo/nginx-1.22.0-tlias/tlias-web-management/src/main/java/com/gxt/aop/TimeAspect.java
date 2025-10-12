package com.gxt.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
//@Aspect
@Slf4j
public class TimeAspect {
    @Around("execution(* com.gxt.service.*.*(..))") //所以接口所有类，切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.记录开始时间
        long begin = System.currentTimeMillis();

        //2.调用原始方法运行
        Object result = joinPoint.proceed();

        //3.记录结束时间
        long end = System.currentTimeMillis();

        //getSignature() 拿到方法签名
        log.info(joinPoint.getSignature() + "方法执行耗时： {}ms",end - begin);

        return result;
    }
}
