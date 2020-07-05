package br.com.javaBackendJuniorJonataMicael.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.javaBackendJuniorJonataMicael"))
                .paths(PathSelectors.regex("/api/colaborador.*"))
                .build()
                .apiInfo(metaInfo());
    }

	private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Colaborador API REST",
                "API REST de cadastro colaboradores",
                "0.0.1",
                "Terms of Service",
                new Contact("Jonata Micael", "https://github.com/devjony?tab=repositories",
                        "jonatamicael@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
