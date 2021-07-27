package com.zemoso.blinklist.aspect;

import com.zemoso.blinklist.controller.BookController;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before(value = "execution(* com.zemoso.blinklist.controller.BookController.getAllBooks())")
    public void logBefore(JoinPoint joinPoint){
        logger.warn(" logBefore() is running! ");
    }

    @After(value = "execution(* com.zemoso.blinklist.controller.BookController.getAllBooks())")
    public void logAfter(JoinPoint joinPoint){
        log.warn(" logAfter() is running! ");
    }

}
