package com.delgadotrueba.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
    // http://localhost:8080/api/v0/swagger-ui.html

    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(this.apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        			.title("Api-Rest-with-Spring-Boot")
        			.description("Repositorio en Github. https://github.com/DelgadoTrueba/Api-Rest-with-Spring-Boot")
        			.version("0.0.0")
        			.build();
    }
}