package com.delgadotrueba.api.config;

import static java.util.Collections.singletonList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;

import io.swagger.models.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiModelReader;
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
        		.apis(RequestHandlerSelectors.basePackage("com.delgadotrueba.api"))              
        		.paths(PathSelectors.any())                          
        		.build()
        		.apiInfo(this.apiInfo())
        	    .produces(DEFAULT_PRODUCES_AND_CONSUMES)
        	    .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
        	    .useDefaultResponseMessages(true)
        	    .securitySchemes( this.schemeList() )
        	    .securityContexts( this.securityContextList() );
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        			.title(TITLE)
        			.description(DESCRIPTION)
        			.version(VERSION)
        			.build();
    }
    
    private List<SecurityScheme> schemeList() {
        return Arrays.asList(
        		new BasicAuth("Basic"),
                new ApiKey("Bearer", "Authorization", "header")
                );
    }
         
    private List<SecurityContext> securityContextList() {
    	/*
    	SecurityContext securityContextBasicAuth = 
    			SecurityContext.builder()
		            .securityReferences(this.defaultAuth())
		            .forPaths(PathSelectors.ant("/employees/**")) 
		            .forHttpMethods(this.methodSelector())
		            .build();
		*/
    	
    	SecurityContext securityContextJWT = 
    			SecurityContext.builder()
		            .securityReferences(this.defaultAuth())
		            .forPaths(PathSelectors.ant("/employees/**")) 
		            .forHttpMethods(this.methodSelector())
		            .build();
    	
    	ArrayList<SecurityContext> context = new ArrayList<SecurityContext>();
    	
    	context.add(securityContextJWT);
    	
    	return context;
     }
     
	private List<SecurityReference> defaultAuth() {
	    
		AuthorizationScope authorizationScope
		        = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		
		return singletonList(
		    new SecurityReference("Bearer", authorizationScopes)
		    ); 
	 }
     
	private Predicate<org.springframework.http.HttpMethod> methodSelector() {     
	    return new Predicate<org.springframework.http.HttpMethod>() {
	 		@Override
	 		public boolean apply(org.springframework.http.HttpMethod input) {
	 			return input.matches("POST") || input.matches("PUT")  || input.matches("DELETE");
	 		}
	    };
	}
   
	/*PRUEBA*/
    @Primary
    @Bean
    public ApiListingScanner addExtraOperations(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager)
    {
        return new FormLoginOperations(apiDescriptionReader, apiModelReader, pluginsManager);
    }
}