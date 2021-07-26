package com.zemoso.blinklist.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Slf4j
@Aspect
@Component
public class AopAfterThrowingException {
    @AfterThrowing(value = "execution(* com.zemoso.blinklist.controller.*.*(..))",throwing="ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex)
    {
        log.error("After Throwing exception in method:"+joinPoint.getSignature());
        log.error("Exception is:"+ex.getMessage());
    }
}
