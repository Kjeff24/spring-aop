package com.bexos.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

    @Before("execution(* com.bexos.aop.service.UserService.addUser(..))")
    public void logs() {
        System.out.println("AOP BEFORE");
    }
}
