package com.sda10.finalproject.projectmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest()
                .permitAll();
    }
        @Bean
        CorsConfigurationSource corsConfigurationSource () {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfigurationSource = new CorsConfiguration().applyPermitDefaultValues();
        corsConfigurationSource.addAllowedMethod(HttpMethod.DELETE);
            corsConfigurationSource.addAllowedMethod(HttpMethod.PUT);
        source.registerCorsConfiguration("/**", corsConfigurationSource);
        return source;
    }
    }


