package com.slxy.www.config;

/**
 * @author zhengql
 * @description
 * @className DocumentDirectoryCustomizer
 * @create 2018年04月11日  10:42
 */

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * 内嵌Tomcat 运行项目目录
 */
@Configuration
public class DocumentDirectoryCustomizer implements EmbeddedServletContainerCustomizer {
    public void customize(ConfigurableEmbeddedServletContainer container) {
        //项目目录
        container.setDocumentRoot(new File(
                System.getProperty("user.dir") + "/src/main/webapp"));
    }
}

