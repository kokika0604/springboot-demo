package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    
    // メソッドを指定する
    @Before("execution(* com.example.demo.controller.HelloController.index())")
    public void startLog(JoinPoint jp)
    {
        log.info("{}: Before", jp.getSignature());
    }

    // beanを指定する
    @After("bean(commentController)")
    public void endLog(JoinPoint jp)
    {
        log.info("{}: After", jp.getSignature());
    }
}
