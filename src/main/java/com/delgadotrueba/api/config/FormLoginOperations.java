package com.delgadotrueba.api.config;

import static java.util.Collections.singletonList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import static java.util.Collections.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;

import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Operation;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;


public class FormLoginOperations extends ApiListingScanner
{
    @Autowired
    private TypeResolver typeResolver;
    
    private final HashSet<String> TAGS = new HashSet<String>(Arrays.asList("user-resource"));
    
    private final String SUMMARY = "Generate a token given an authentication of a user through basic auth.";

    @Autowired
    public FormLoginOperations(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager)
    {
        super(apiDescriptionReader, apiModelReader, pluginsManager);
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context)
    {
        final Multimap<String, ApiListing> def = super.scan(context);

        final List<ApiDescription> apis = new LinkedList<>();

        final List<Operation> operations = new ArrayList<>();
        
        operations.add(
        		new OperationBuilder(new CachingOperationNameGenerator())
        			.authorizations(defaultAuth())
		            .method(HttpMethod.POST)
		            .uniqueId("token")
		            .summary(this.SUMMARY)
		            .tags(this.TAGS)
		            .responseMessages(responseMessages())
		            .build()
            );
        
        apis.add(new ApiDescription("GroupName", "/users/token", "Authentication documentation", operations, false));

        def.put("token", new ApiListingBuilder(context.getDocumentationContext().getApiDescriptionOrdering())
            .apis(apis)
            .description("Custom authentication")
            .build()
        );
        

        return def;
    }
    
    private List<SecurityReference> defaultAuth() {
	    
		AuthorizationScope authorizationScope
		        = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		
		return singletonList(
		    new SecurityReference("Basic", authorizationScopes)
		    ); 
	 }
    
    /**
     * @return Set of response messages that overide the default/global response messages
     */
    private Set<ResponseMessage> responseMessages() { 
      HashSet<ResponseMessage> responses = new HashSet<ResponseMessage>();
      
      ResponseMessage response200 = new ResponseMessageBuilder()
		      .code(200)
		      .message("OK")
		      .responseModel(null)
		      .build();
      
      ResponseMessage response401 = new ResponseMessageBuilder()
    	      .code(401)
    	      .message("No credentials found in header or invalid user/password")
    	      .responseModel(null)
    	      .build();
      
      responses.add(response200);
      responses.add(response401);

      return responses;
    }

    public boolean supports(DocumentationType delimiter) {
      return DocumentationType.SWAGGER_2.equals(delimiter);
    }
}