package com.hamza.springboot.training.thymeleaf.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {


    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.hamza.springboot.training.thymeleaf.controller.*.*(..))")
    private void forControllerPackage(){}


    @Pointcut("execution(* com.hamza.springboot.training.thymeleaf.dao.*.*(..))")
    private void fordaoPackage(){}


    @Pointcut("execution(* com.hamza.springboot.training.thymeleaf.service.*.*(..))")
    private void forservicePackage(){}

    @Pointcut("forControllerPackage() || fordaoPackage() || forservicePackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        // displaying method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        myLogger.info("====>>> in @before: calling method: "+theMethod);

        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop through and display args
        for (Object arg:args){
            myLogger.info("===>>> argument: "+arg);
        }

    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult")
    public void afterReturning(JoinPoint joinPoint, Object theResult){

        // displaying method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        myLogger.info("====>>> in @before: calling method: "+theMethod);

        //displaying data returning
        myLogger.info("===>>> result: "+ theResult);

    }


}
