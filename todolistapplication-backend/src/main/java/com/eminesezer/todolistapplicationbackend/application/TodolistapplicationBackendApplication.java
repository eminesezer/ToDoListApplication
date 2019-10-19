package com.eminesezer.todolistapplicationbackend.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.eminesezer.todolistapplicationbackend.*")
public class TodolistapplicationBackendApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(TodolistapplicationBackendApplication.class, args);
		context.getBean(DataFiller.class).dataFill();
	}
}