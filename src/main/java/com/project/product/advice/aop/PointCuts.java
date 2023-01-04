package com.project.product.advice.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    @Pointcut("execution(* *..*Controller.*(..))")
    public void allLogController(){}

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}
}
