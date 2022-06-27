package com.lyscharlie.common;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final AdminServerProperties adminServerProperties;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		String adminContextPath =  adminServerProperties.getContextPath();
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl(adminContextPath + "/");

		return http.authorizeRequests()
				.antMatchers(adminContextPath + "/assets/**").permitAll() // 静态文件允许访问
				.antMatchers(adminContextPath + "/login").permitAll() //登录页面允许访问
				.anyRequest().authenticated()//其他所有请求需要登录
				.and().formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler)//登录页面配置，用于替换security默认页面
				.and().logout().logoutUrl(adminContextPath + "/logout") //登出页面配置，用于替换security默认页面
				.and().httpBasic().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.ignoringAntMatchers(adminContextPath + "/instances", adminContextPath + "/actuator/**")
				.and().build();
	}

}
