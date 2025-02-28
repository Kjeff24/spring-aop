package com.bexos.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

    @Pointcut("execution(* com.bexos.aop.service.*.*(..))")
    public void serviceMethods() {}

    @Before("execution(* com.bexos.aop.service.UserService.addUser(..))")
    public void beforeAdvice() {
        System.out.println("AOP BEFORE");
    }

    @After("serviceMethods()")
    public void afterAdvice() {
        System.out.println("[AFTER] Method execution completed");
    }

    @Around("serviceMethods()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[AROUND] Before executing: " + joinPoint.getSignature());
        Object result = joinPoint.proceed();
        System.out.println("[AROUND] After executing: " + joinPoint.getSignature());
        return result;
    }
}
