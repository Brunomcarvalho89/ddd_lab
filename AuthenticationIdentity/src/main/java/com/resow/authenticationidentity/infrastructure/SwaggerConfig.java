package com.resow.authenticationidentity.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 * 
 * /AuthenticationIdentity/api/v1/swagger-ui/index.html#/
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket newsApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.resow.authenticationidentity.infrastructure.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Api for user Authentication and Identity")
                .description("Documentation API for authentication and user identification.")
                .version("1.0")
                .build();
    }
}
