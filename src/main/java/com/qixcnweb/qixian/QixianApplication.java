package com.qixcnweb.qixian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.qixcnweb.qixian.dao")
public class QixianApplication {

	public static void main(String[] args) {
		SpringApplication.run(QixianApplication.class, args);
	}
}
