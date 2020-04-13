package com.temple.polymorphic.toolbox.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//@Configuration
//@EnableWebSecurity
//@EnableWebMvc
//@ComponentScan
//public class AppConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests().antMatchers("/api/**").access("hasRole('ROLE_ADMIN')")
////                .antMatchers("/client/**").access("hasRole('ROLE_USER')")
////                .antMatchers("/**").permitAll().and().formLogin();
//        http.authorizeRequests().antMatchers("/**").access("hasRole('ROLE_ADMIN')").and().formLogin();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth){
//        auth.authenticationProvider(new SecurityConfig());
//    }
//
//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/jsp/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
//}