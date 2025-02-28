# Spring AOP with Spring Boot

## Introduction
Spring AOP (Aspect-Oriented Programming) is a powerful feature of the Spring Framework that allows developers to separate cross-cutting concerns, such as logging, security, and transaction management, from business logic. AOP in Spring is implemented using proxies and supports various types of advice.

## Key Concepts
- **Aspect**: A module that contains cross-cutting concerns.
- **Advice**: The action taken by an aspect (e.g., before, after, around).
- **Join Point**: A point in the execution flow where an aspect can be applied.
- **Pointcut**: An expression that matches join points.
- **Target**: The object being advised by an aspect.
- **Proxy**: A wrapper around the target object to apply advice.

## Adding Spring AOP to Your Project
Include the following dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

## Creating an Aspect
### 1. Define an Aspect Class

```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    
    @Pointcut("execution(* com.example.service.*.*(..))")
    public void serviceMethods() {}
    
    @Before("serviceMethods()")
    public void beforeAdvice() {
        System.out.println("[BEFORE] Method execution started");
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
```

### 2. Applying AOP to a Service

```java
import org.springframework.stereotype.Service;

@Service
public class UserService {
    List<User> users = new ArrayList<User>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
```

### 3. Running the Application

```java
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class AopApplication implements CommandLineRunner {
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userService.addUser(User.builder().age(20).name("user").build());
        userService.getAllUsers();
    }
}
```

## Expected Output
```
[BEFORE] Method execution started
[AROUND] Before executing: void com.example.service.SampleService.performTask()
Executing business logic...
[AROUND] After executing: void com.example.service.SampleService.performTask()
[AFTER] Method execution completed
```

## Conclusion
Spring AOP provides a flexible way to handle cross-cutting concerns without modifying core business logic. By using `@Aspect`, `@Pointcut`, and advice annotations, you can effectively apply AOP in your Spring Boot applications.

