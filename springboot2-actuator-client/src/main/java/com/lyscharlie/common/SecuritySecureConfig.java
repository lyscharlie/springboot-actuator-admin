package com.lyscharlie.common;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecuritySecureConfig {

    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeRequests()
				//拦截所有endpoint，拥有ACTUATOR_ADMIN角色可访问，否则需登录
				.requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("actuator_admin")
				//静态文件允许访问
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				//根路径允许访问
				.antMatchers("/").permitAll()
				//所有请求路径可以访问
				.antMatchers("/**").permitAll()
				.and().httpBasic()
				.and().build();
	}

}


