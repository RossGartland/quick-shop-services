package qs.qsgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class QsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(QsGatewayApplication.class, args);
	}

}
