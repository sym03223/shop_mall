package com.practice.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomPasswordEncoding customPasswordEncoding;

    public SecurityConfig(CustomPasswordEncoding customPasswordEncoding) {
        this.customPasswordEncoding = customPasswordEncoding;
    }

    @Override
    protected void configure(HttpSecurity http){

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return customPasswordEncoding.passwordEncoder("sha256");
    }

}
