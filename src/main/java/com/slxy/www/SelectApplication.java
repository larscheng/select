package com.slxy.www;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@MapperScan("com.slxy.www.dao")
@SpringBootApplication
public class SelectApplication  extends SpringBootServletInitializer {
	@Override
	protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(SelectApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SelectApplication.class, args);
	}
}
