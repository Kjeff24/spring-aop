package com.bexos.aop;

import com.bexos.aop.model.User;
import com.bexos.aop.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AopApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AopApplication.class, args);

		UserService userService = context.getBean(UserService.class);

		userService.addUser(User.builder().age(20).name("user").build());
	}

}
