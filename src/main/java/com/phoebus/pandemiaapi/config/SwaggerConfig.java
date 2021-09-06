package com.phoebus.pandemiaapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig {

	
	@Bean
	public Docket pandemiaApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.phoebus.pandemiaapi"))
				.paths(regex("/hospital.*"))
				.build()
				.apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Pandemic Combat Aid System",
				"Api RESTFul - Sistema de Ajuda ao combate da Pandemia",
				"1.0",
				"Terms of Service",
				new Contact("Evandson Oliveira","http://www.linkedin.com/in/evandsonoliveira","oliveranza@gmail.com"),
				"Apache Licence Version 2.0",
				"https://www.apache.org/license.html", new ArrayList<VendorExtension>()
				);
		return apiInfo;
				
	}
	
	
}
