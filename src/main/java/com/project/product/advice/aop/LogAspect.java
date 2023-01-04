package com.project.product.advice.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Before("com.project.product.advice.aop.PointCuts.allLogController()")
    public void doLogBefore(JoinPoint joinPoint){
        log.info("Before Controller Log {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.project.product.advice.aop.PointCuts.allLogController()", returning = "result")
    public void doLogReturn(JoinPoint joinPoint, Object result){
        log.info("Return Controller Log {}, return {}", joinPoint.getSignature(),result);
    }

    @AfterThrowing(value = "com.project.product.advice.aop.PointCuts.allLogController()", throwing = "e")
    public void doLogThrowing(JoinPoint joinPoint, Exception e){
        log.info("Throwing Controller Log {}, message {}", joinPoint.getSignature(),e);
    }
}
