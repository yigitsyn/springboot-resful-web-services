package com.javapractice.springboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



//Swagger Documentation template
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Rest API Documentation",
				description = "Spring Boot Rest API Demo",
				version = "v1.0",
				contact = @Contact(
						name = "Yigit",
						email = "myigitsyn@gmail.com",
						url ="https://github.com/yigitsyn/springboot-resful-web-services"
				)
		)
)
@SpringBootApplication
public class SpringbootResfulWebServicesApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootResfulWebServicesApplication.class, args);
	}

}
