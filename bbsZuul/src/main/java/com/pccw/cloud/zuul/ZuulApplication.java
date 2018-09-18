package com.pccw.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * 获取OAuth2 code http://localhost:1010/uaa/oauth/authorize?client_id=zuul&response_type=code&redirect_uri=http://localhost:7777/&state=45678
 * 获取token http://localhost:1010/uaa/oauth/token?grant_type=authorization_code&client=zuul&redirect_uri=http://localhost:7777/&code=8KOZlv
 * */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}
}
