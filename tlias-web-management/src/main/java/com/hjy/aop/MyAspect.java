package com.hjy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(1)
public class MyAspect {

    @Around("execution(* com.hjy.service.impl.*.*(..))")
    //@Around("execution(* com.hjy.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("开始执行 {}",pjp.getSignature());

        String className = pjp.getTarget().getClass().getName();
        pjp.getSignature().getName();
        pjp.getArgs() ;


        Object obj = pjp.proceed();
        log.info("结束执行 {}",pjp.getSignature());
        return obj;
    }
}
