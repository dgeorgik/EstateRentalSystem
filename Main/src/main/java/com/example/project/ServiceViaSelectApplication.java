package com.example.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.project.controller", "com.example.project.services", "com.example.project.repository", "com.example.project.components"})
public class ServiceViaSelectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceViaSelectApplication.class, args);
	}

	@Bean
	public graphql.schema.GraphQLScalarType extendedScalarLong() {
		return graphql.scalars.ExtendedScalars.GraphQLLong;
	}
}
