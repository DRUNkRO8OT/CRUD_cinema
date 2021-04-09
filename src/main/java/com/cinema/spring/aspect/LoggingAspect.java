package com.cinema.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger log = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* com.cinema.spring.dao.*.*(..))")
    public Object aroundAllRepositoryMethodAdvice(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature =
                (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();

        log.info("Begin of " + methodName);

        Object targetMethodResult = null;

        try {
            targetMethodResult = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            log.info("An exception was caught: " + e);
            throw e;
        }

        log.info("End of " + methodName);

        return targetMethodResult;
    }
}
