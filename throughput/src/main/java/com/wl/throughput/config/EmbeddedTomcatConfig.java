package com.wl.throughput.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/18 14:54
 */
@Configuration
public class EmbeddedTomcatConfig {

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(){
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addConnectorCustomizers(new CustomTomcatConnectorCustomizer());
        tomcatServletWebServerFactory.setPort(8090);
        tomcatServletWebServerFactory.setContextPath("/API");
        return tomcatServletWebServerFactory;
    }



}
