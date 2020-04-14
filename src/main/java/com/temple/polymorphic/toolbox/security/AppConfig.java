package com.temple.polymorphic.toolbox.security;

import com.temple.polymorphic.toolbox.services.MySimpleUrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.Cookie;

@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/api/**").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/client/**").access("hasRole('ROLE_USER')")
//                .antMatchers("/**").permitAll().and().formLogin();

        //http.authorizeRequests().antMatchers("src/main/resources/public/**","/css/**","/js/**").permitAll().and().authorizeRequests().antMatchers("/**").authenticated().and().formLogin();

        //http.authorizeRequests().antMatchers("/","/client/**").hasRole("USER").and().authorizeRequests().antMatchers("/**").hasRole("ADMIN").and().formLogin();

        http.authorizeRequests().antMatchers("src/main/resources/public/**","/css/**","/js/**").permitAll()
                .and().authorizeRequests().antMatchers("/","/home","/client/**").hasRole("USER")
                .and().authorizeRequests().antMatchers("/**").hasRole("ADMIN")
                .and().formLogin().successHandler(mySimpleUrlAuthenticationSuccessHandler)
                .and().exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new SecurityConfig());
        auth.inMemoryAuthentication().withUser("tuk12920@temple.edu").password("user").roles("USER").and().withUser("admin").password("admin").roles("ADMIN","USER");

    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}