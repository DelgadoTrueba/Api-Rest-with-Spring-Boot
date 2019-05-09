package com.delgadotrueba.api.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {   
	
	private static final String TITLE = "Api-Rest-with-Spring-Boot";
	private static final String DESCRIPTION = "Repositorio en Github. https://github.com/DelgadoTrueba/Api-Rest-with-Spring-Boot";
	private static final String VERSION = "0.0.0";
	
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
        		.select()                                  
        		.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))              
        		.paths(PathSelectors.any())                          
        		.build()
        		.apiInfo(this.apiInfo())
        	    .produces(DEFAULT_PRODUCES_AND_CONSUMES)
        	    .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        			.title(TITLE)
        			.description(DESCRIPTION)
        			.version(VERSION)
        			.build();
    }
}