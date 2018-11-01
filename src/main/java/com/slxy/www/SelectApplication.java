package com.slxy.www;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.slxy.www.dao")
@EnableSwagger2
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
@SpringBootApplication

public class SelectApplication  extends SpringBootServletInitializer {
	@Override
	protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(SelectApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SelectApplication.class, args);
	}




	//https配置
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer(){
//		TomcatEmbeddedServletContainerFactory tomcat=new TomcatEmbeddedServletContainerFactory(){
//			@Override
//			protected void postProcessContext(Context context) {
//				SecurityConstraint securityConstraint=new SecurityConstraint();
//				securityConstraint.setUserConstraint("CONFIDENTIAL");//confidential
//				SecurityCollection collection=new SecurityCollection();
//				collection.addPattern("/*");
//				securityConstraint.addCollection(collection);
//				context.addConstraint(securityConstraint);
//			}
//		};
//		tomcat.addAdditionalTomcatConnectors(httpConnector());
//		return tomcat;
//	}
//
//	@Bean
//	public Connector httpConnector(){
//		Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setScheme("http");
//		connector.setPort(8080);
//		connector.setSecure(false);
//		connector.setRedirectPort(8088);
//		return connector;
//	}
}
