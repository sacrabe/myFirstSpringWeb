package com.hjy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class MyLogAspect  {
    @Around("@annotation(com.hjy.anno.LogOperation)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("开始执行 {}",pjp.getSignature().getName());
        Object proceed = pjp.proceed();
        log.info("结束执行 {}",pjp.getSignature().getName());
        return proceed;
    }

}
