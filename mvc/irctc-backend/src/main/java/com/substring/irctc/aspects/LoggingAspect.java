package com.substring.irctc.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    //cross cutting concern logic
    @Around("execution(* com.substring.irctc.service.impl.*.*(..))")
    public Object logBeforeMethod(ProceedingJoinPoint joinPoint) throws Throwable {



        long startTime= System.currentTimeMillis();
        logger.info("Before method Execution {} ", joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();


        long endTime= System.currentTimeMillis();
        logger.info("Time taken {} ms", endTime-startTime);
        logger.info("After  method Execution {} ", joinPoint.getSignature().getName());

        return result;
    }

//    @Before -  before method execution
//    @After - after method finishes
//    @AfterReturning  - after successful return
//    @AfterThrowing - after throwing exception
//    @Around - before and after

}
