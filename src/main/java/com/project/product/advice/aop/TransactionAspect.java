package com.project.product.advice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TransactionAspect {

    @Before("com.project.product.advice.aop.PointCuts.allService()")
    public void doTransactionBefore(JoinPoint joinPoint){
        log.info("Before Transaction Log {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.project.product.advice.aop.PointCuts.allService()", returning = "result")
    public void doTransactionReturn(JoinPoint joinPoint, Object result){
        log.info("Return Transaction Log {}, return {}", joinPoint.getSignature(),result);
    }

    @AfterThrowing(value = "com.project.product.advice.aop.PointCuts.allService()", throwing = "e")
    public void doTransactionThrowing(JoinPoint joinPoint, Exception e){
        log.info("Throwing Transaction Log {}, message {}", joinPoint.getSignature(),e);
    }
}
