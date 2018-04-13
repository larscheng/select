package com.slxy.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger2 V2.8.0 配置
 *
 * @author Leaves
 * @version 1.1.0
 * @date 2017/11/21
 */
@EnableSwagger2
@Configuration
public class Swagger2Configuration {

//    @Autowired
//    private TypeResolver typeResolver;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.slxy.www.web"))
                //select any api that matches one of these paths
                .paths(PathSelectors.any())
                .build()
                //.pathMapping("/")
                //.directModelSubstitute(LocalDate.class, String.class)
                //.genericModelSubstitutes(ResponseEntity.class)

                //.alternateTypeRules(newRule(typeResolver.resolve(DeferredResult.class,
                //        typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                //        typeResolver.resolve(WildcardType.class)))

                //.useDefaultResponseMessages(false)

                //Allows globally overriding response messages for different http methods. In this example we override the 500 error code for all `GET`s
                //.globalResponseMessage(RequestMethod.GET,
                //        newArrayList(new ResponseMessageBuilder()
                //                .code(500)
                //                .message("500 message")
                //                .responseModel(new ModelRef("Error"))
                //                .build()))

                //Sets up the security schemes used to protect the apis. Supported schemes are ApiKey, BasicAuth and OAuth
                .securitySchemes(newArrayList(apiKeySecurityScheme()))
                .securityContexts(newArrayList(apiKeySecurityContext()))

                //.enableUrlTemplating(false)

                //Allows globally configuration of default path-/request-/headerparameters which are common for every rest operation of the api, but aren`t needed in spring controller method signature (for example authenticaton information). Parameters added here will be part of every API Operation in the generated swagger specification.
                //.globalOperationParameters(
                //        newArrayList(new ParameterBuilder()
                //                .name("someGlobalParameter")
                //                .description("Description of someGlobalParameter")
                //                .modelRef(new ModelRef("string"))
                //                .parameterType("query")
                //                .required(true)
                //                .build()))
                //
                //.tags(new Tag("", ""))
                //
                //.additionalModels(typeResolver.resolve(AdditionalModel.class))
                ;
    }

//    private Predicate<String> paths() {
//        return or(
//                regex("/business.*"),
//                regex("/some.*"),
//                regex("/contacts.*"),
//                regex("/pet.*"),
//                regex("/springsRestController.*"),
//                regex("/test.*"));
//    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("zhengql", "", "zhengql@senthink.com");
        return new ApiInfoBuilder()
                .title("Select API")
                .description("毕业论文选题系统接口API，其中大部分接口均支持测试，初始化接口均为跳转功能故不支持测试，如有问题请联系工程师小哥哥")
                .termsOfServiceUrl("")
                .contact(contact)
                .license("Select License Version 1.0.0")
                .licenseUrl("")
                .version("1.0.0")
                .build();
    }

    private SecurityScheme apiKeySecurityScheme() {
        return new ApiKey("Token", "Authorization", "header");
    }

    private SecurityContext apiKeySecurityContext() {
        AuthorizationScope[] authScopes = new AuthorizationScope[1];
        authScopes[0] = new AuthorizationScopeBuilder()
                .scope("read")
                .description("access")
                .build();
        SecurityReference securityReference = SecurityReference.builder()
                .reference("Token")
                .scopes(authScopes)
                .build();

        return SecurityContext.builder().securityReferences(newArrayList(securityReference)).build();
    }

    private SecurityScheme basicAuthSecurityScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityContext basicAuthSecurityContext() {
        AuthorizationScope[] authScopes = new AuthorizationScope[1];
        authScopes[0] = new AuthorizationScopeBuilder()
                .scope("global")
                .description("access")
                .build();
        SecurityReference securityReference = SecurityReference.builder()
                .reference("basicAuth")
                .scopes(authScopes)
                .build();

        return SecurityContext.builder().securityReferences(newArrayList(securityReference)).build();
    }

//    @Bean
//    SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//                .clientId("test-app-client-id")
//                .clientSecret("test-app-client-secret")
//                .realm("test-app-realm")
//                .appName("test-app")
//                .scopeSeparator(",")
//                .additionalQueryStringParams(null)
//                .useBasicAuthenticationWithAccessCodeGrant(false)
//                .build();
//    }

//    @Bean
//    UiConfiguration uiConfig() {
//        return UiConfigurationBuilder.builder()
//                .deepLinking(true)
//                .displayOperationId(false)
//                .defaultModelsExpandDepth(1)
//                .defaultModelExpandDepth(1)
//                .defaultModelRendering(ModelRendering.EXAMPLE)
//                .displayRequestDuration(false)
//                .docExpansion(DocExpansion.NONE)
//                .filter(false)
//                .maxDisplayedTags(null)
//                .operationsSorter(OperationsSorter.ALPHA)
//                .showExtensions(false)
//                .tagsSorter(TagsSorter.ALPHA)
//                .validatorUrl(null)
//                .build();
//    }
}