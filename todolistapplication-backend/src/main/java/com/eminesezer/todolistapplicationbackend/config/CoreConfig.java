package com.eminesezer.todolistapplicationbackend.config;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration()
@EnableJpaRepositories(basePackages = {"com.eminesezer.todolistapplicationbackend.repository"})
@EntityScan(basePackages = {"com.eminesezer.todolistapplicationbackend.entity"})
@ComponentScan(basePackages = {"com.eminesezer.todolistapplicationbackend.*"})
public class CoreConfig {
    @Value("${spring.h2.console.path}")
    private String url;

    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings(url + "/*");
        return registrationBean;
    }
}
