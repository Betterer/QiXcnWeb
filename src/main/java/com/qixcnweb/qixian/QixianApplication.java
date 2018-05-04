package com.qixcnweb.qixian;

import com.qixcnweb.qixian.controller.WxAuthController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.qixcnweb.qixian.*"})
@MapperScan("com.qixcnweb.qixian.dao")
public class QixianApplication {

	public static void main(String[] args) {
		SpringApplication.run(QixianApplication.class, args);
	}
}
