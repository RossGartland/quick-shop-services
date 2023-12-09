package com.qsorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QsOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QsOrderServiceApplication.class, args);
	}

}
