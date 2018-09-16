package com.pccw.cloud.eurzka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurzkaApplication {
	private static Logger log_ = LoggerFactory.getLogger(EurzkaApplication.class);
	
	public static void main(String[] args) {
		log_.info("eurzka server start");
		SpringApplication.run(EurzkaApplication.class, args);
	}
}
