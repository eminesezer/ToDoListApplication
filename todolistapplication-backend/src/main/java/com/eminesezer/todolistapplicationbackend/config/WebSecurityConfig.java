package com.eminesezer.todolistapplicationbackend.config;

import com.eminesezer.todolistapplicationbackend.entrypoint.UnAuthorizedResponseAuthenticationEntryPoint;
import com.eminesezer.todolistapplicationbackend.filter.TokenAuthorizationOncePerRequestFilter;
import com.eminesezer.todolistapplicationbackend.services.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig
        extends WebSecurityConfigurerAdapter {

  @Autowired
  private UnAuthorizedResponseAuthenticationEntryPoint unAuthorizedResponseAuthenticationEntryPoint;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private TokenAuthorizationOncePerRequestFilter tokenAuthorizationOncePerRequestFilter;

  @Value("${jwt.get.token.uri}")
  private String authenticationPath;
  @Value("${spring.h2.console.path}")
  private String url;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder());
  }
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unAuthorizedResponseAuthenticationEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
            .authorizeRequests()
            .anyRequest().authenticated();

    httpSecurity
            .addFilterBefore(tokenAuthorizationOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class);

    httpSecurity
            .headers()
            .frameOptions().sameOrigin()  //H2 Console Needs this setting
            .cacheControl(); //disable caching
  }

  @Override
  public void configure(WebSecurity webSecurity) throws Exception {
    webSecurity
            .ignoring()
            .antMatchers(
                    HttpMethod.POST,
                    authenticationPath
            )
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .and()
            .ignoring()
            .antMatchers(
                    HttpMethod.GET,
                    "/" //Other Stuff You want to Ignore
            )
            .and()
            .ignoring()
            .antMatchers(url+"/**/**");//Should not be in Production!
  }
}