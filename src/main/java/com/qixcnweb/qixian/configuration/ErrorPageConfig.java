package com.qixcnweb.qixian.configuration;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 错误页面配置类
 * Created by dingxiaochi on 2018/3/9.
 */
@Configuration
public class ErrorPageConfig {
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
        return new MyCustomizer();
    }

    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {

        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403/");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404/");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

            container.addErrorPages(error403Page,error404Page,error500Page);
        }

    }
}
