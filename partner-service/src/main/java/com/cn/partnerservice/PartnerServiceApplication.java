package com.cn.partnerservice;

import com.cn.partnerservice.helpers.AzureProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties({AzureProperties.class})
public class PartnerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartnerServiceApplication.class, args);
	}

}
