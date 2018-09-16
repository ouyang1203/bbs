package com.pccw.cloud.zuul.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * zuul熔断
 * @Component将本类注册到spring中
 * */
@Component
public class ApiUserFallbackProvider implements ZuulFallbackProvider{
	@Value("${zuul.api.user.name}")
	String serviceName;
	
	/**
	 * 指定本回调方法适用于哪个服务
	 * */
	@Override
	public String getRoute() {
		return serviceName;
	}


	@Override
	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				String s = "The service :"+serviceName+" is unavailable.";
				return new ByteArrayInputStream(s.getBytes());
			}
			
			@Override
			public String getStatusText() throws IOException {
				return "OK";
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}
			
			@Override
			public void close() {
				
			}
		};
	}

}
