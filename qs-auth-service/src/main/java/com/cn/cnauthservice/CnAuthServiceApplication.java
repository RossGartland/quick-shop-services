package com.cn.cnauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CnAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnAuthServiceApplication.class, args);
	}

}
