package com.pccw.cloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication {
	private static Logger log_ = LoggerFactory.getLogger(ConfigApplication.class);
	
	public static void main(String[] args) {
		log_.info("config server start");
		SpringApplication.run(ConfigApplication.class, args);
	}
}
