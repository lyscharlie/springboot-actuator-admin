package com.lyscharlie.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

	@Value("${swagger.enable}")
	private boolean enableSwagger;

	@Bean
	public Docket customDocket() {
		return new Docket(DocumentationType.OAS_30)
				.enable(enableSwagger)
				.apiInfo(apiInfo())
				.groupName("all")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.lyscharlie.web"))
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	public Docket createAreaRestApi() {
		return new Docket(DocumentationType.OAS_30)
				.enable(enableSwagger)
				.apiInfo(apiInfo())
				.groupName("area")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.lyscharlie.web.area"))
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	public Docket createTestRestApi() {
		return new Docket(DocumentationType.OAS_30)
				.enable(enableSwagger)
				.apiInfo(apiInfo())
				.groupName("test")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.lyscharlie.web.test"))
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	public Docket createUserRestApi() {
		return new Docket(DocumentationType.OAS_30)
				.enable(enableSwagger)
				.apiInfo(apiInfo())
				.groupName("user")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.lyscharlie.web.user"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("lyscharlie", "www.lyscharlie.com", "lyscharlie@hotmail.com");
		return new ApiInfoBuilder()
				.title("我是文档标题")
				.description("我是文档描述")
				.contact(contact)   // 联系方式
				.version("1.0.0")  // 版本
				.build();
	}

	/**
	 * 增加如下配置可解决Spring Boot 6.x 与Swagger 3.0.0 不兼容问题
	 **/
	@Bean
	public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
		List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
		Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
		allEndpoints.addAll(webEndpoints);
		allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
		allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
		String basePath = webEndpointProperties.getBasePath();
		EndpointMapping endpointMapping = new EndpointMapping(basePath);
		boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
		return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
	}
	private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
		return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
	}
}
