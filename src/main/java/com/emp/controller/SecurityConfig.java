/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.controller;

import com.emp.CustomLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author ravi
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    

    @Autowired
    CustomLogin succesHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.sessionManagement().maximumSessions(90);
        
        
        
         //http.csrf().disable().authorizeRequests().antMatchers("/api/employee/**").hasAnyAuthority("admin").and().formLogin().successHandler(succesHandler);
         http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET,"/api/employee/").hasAnyAuthority("admin","user").and().formLogin().successHandler(succesHandler);
        http.csrf().disable().authorizeRequests().antMatchers("/adminPage").hasAnyAuthority("admin").and().formLogin().successHandler(succesHandler);
        http.csrf().disable().authorizeRequests().antMatchers("/").hasAnyAuthority("user").and().formLogin().successHandler(succesHandler);
        http.csrf().disable().authorizeRequests().antMatchers("/homePage").hasAnyAuthority("user").and().formLogin().successHandler(succesHandler);
        
    }
    @Autowired 
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();        
        auth.inMemoryAuthentication().withUser("ravi").password(encoder.encode("alla")).roles("user","admin").authorities("admin");
        auth.inMemoryAuthentication().withUser("mradul").password(encoder.encode("dwedi")).roles("user").authorities("user");       
    }
    
}
