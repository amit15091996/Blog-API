package com.blog.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Blog Application : Backend API ").build();
	}

	private List<SecurityContext> securityContexts() {
		return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
	}

	private List<SecurityReference> securityReferences() {
		AuthorizationScope scopes = new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] { scopes }));
	}

	@Bean
	Docket docket() {

		return new Docket(DocumentationType.SWAGGER_2).forCodeGeneration(true).securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKey())).select().apis(RequestHandlerSelectors.basePackage("com.blog"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}
}
