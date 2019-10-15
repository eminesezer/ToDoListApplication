package com.eminesezer.todolistapplicationbackend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.eminesezer.todolistapplicationbackend.*")
public class TodolistapplicationBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistapplicationBackendApplication.class, args);
	}
}
