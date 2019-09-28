package com.example.student_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSercurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new MyUserDetailService();
    }

    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return new MyAuthenticationFailureHandler();
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new MyAuthenticationSuccessHandler();
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/account/**").permitAll()
                .antMatchers(("/**")).hasRole("MEMBER")
                .and().formLogin().loginPage("/account/login").permitAll()
                .failureUrl("/account/login?error")
                .failureHandler(authenticationFailureHandler())
                .successHandler(authenticationSuccessHandler())
                .and().logout().permitAll();
    }
}
