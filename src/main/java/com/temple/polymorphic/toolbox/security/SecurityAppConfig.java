package com.temple.polymorphic.toolbox.security;

import com.temple.polymorphic.toolbox.UserRepository;
import com.temple.polymorphic.toolbox.services.MySimpleUrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityAppConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("src/main/resources/public/**","/css/**","/js/**").permitAll()
//                .and().authorizeRequests().antMatchers("/","/home","/client/**").hasRole("USER")
//                .and().authorizeRequests().antMatchers("/**",).hasRole("ADMIN")
//                .and().formLogin().successHandler(mySimpleUrlAuthenticationSuccessHandler)
//                .and().exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().antMatchers("src/main/resources/public/**","/css/**","/js/**","/console/**").permitAll()
                .and().authorizeRequests().antMatchers("/","/home","/client/**").hasAnyRole("USER","ADMIN")
                .and().authorizeRequests().antMatchers("/**").hasRole("ADMIN")
                .and().formLogin().successHandler(mySimpleUrlAuthenticationSuccessHandler)
                .and().exceptionHandling().accessDeniedPage("/403");
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("tuk12920@temple.edu").password("user").roles("USER").and().withUser("admin").password("admin").roles("ADMIN","USER");

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select u.email as username, u.password as password, 'true' as enabled from users u where u.email=? limit 1")
                .authoritiesByUsernameQuery("select u.email as username, u.role as authority from users u where u.email=?")//.passwordEncoder(getPasswordEncoder()); this is for avoiding auth
                .passwordEncoder(new BCryptPasswordEncoder());

    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    //This Bean is used to avoid encryption and debug the code
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}