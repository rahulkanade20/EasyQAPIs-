package com.EasyQ.API;

import com.EasyQ.API.queuemanagement.SQManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApiApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ApiApplication.class, args);
		SQManager manager = context.getBean(SQManager.class);
		manager.startQ();
	}
}
