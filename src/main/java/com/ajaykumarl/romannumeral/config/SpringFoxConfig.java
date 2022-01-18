package com.ajaykumarl.romannumeral.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	
	@Bean
	 public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(apiInfo())
	    .select()
	    .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	    .paths(PathSelectors.any())
	    .build();
	 }
	 
	 private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Number-to-roman-conversion")
				.description("Number-to-roman-conversion API reference for developers")
				.build();
		}

}
