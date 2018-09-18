package com.pccw.cloud.oauth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.druid.pool.DruidDataSource;
/**
 *@EnableAuthorizationServer设置为Oauth2认证服务器(需要先将对方服务注册到数据库中)
 *@授权访问页面IP http://localhost:1010/uaa/oauth/authorize?client_id=client&response_type=code&redirect_uri=https://www.baidu.com&state=45678
 *@token获取页面 http://localhost:1010/uaa/postOauth2Token?client_id=client&client_secret=secret&redirect_uri=https://www.baidu.com&code=Y0TTIE
 *@继承 WebMvcConfigurerAdapter实现自定义登录页面和授权确认页面
 * */
/**
AuthorizationEndpoint 根据用户认证获得授权码，有下面两个方法：
	/oauth/authorize - GET
	/oauth/authorize - POST
	TokenEndpoint 客户端根据授权码获取 token 
	/oauth/token - GET
	/oauth/token - POST
	CheckTokenEndpoint 可以用于远程解码令牌 
	/oauth/check_token
	WhitelabelApprovalEndpoint 显示授权服务器的确认页。 
	/oauth/confirm_access
	WhitelabelErrorEndpoint 显示授权服务器的错误页 
	/oauth/error
//校验token是否合法
http://localhost:1010/uaa/oauth/check_token?token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsienV1bCJdLCJ1c2VyX25hbWUiOiJ0ZXN0MSIsInNjb3BlIjpbImFwcCJdLCJleHAiOjE1MzcyODM3MjEsImF1dGhvcml0aWVzIjpbImFkbWluIiwib3RoZXIiXSwianRpIjoiMTk1MmE4NGEtMDk4Ny00Nzg5LTg5YmMtYzQ5NWZjYTU4MDI5IiwiY2xpZW50X2lkIjoienV1bCJ9.GrivdCE7OFR_JvAc50B6GPUj99wMbMGWMFdaK6-LBZSd_00rgrONwQZuvJGTsfXmTtNaG_V03gfWXBu2lQyPeBmY6nCazfKp1buqn6EBdRCZdnsale__MTaykNWwaFaAf7cJKFKwQm4OtDDJ2YyblK1yWa_msCQfuBkT-I5wOsAiXrjG6r7suBiW951WFZZsDS1wd0N0gkq_yyo7slzcT4h5xQMEAAfVrEngb-hVghd8Je8Ys3CTYxXwFKGlsjwEHpjszbyxqTNuymX8RMFzSpRMbJKZBF-WS_Z8YJwol3-JA94wemWdN0B1TLp2vNxsfa3XC4sbihMaoWATuAhyZg
 * */
@SpringBootApplication
@EnableAuthorizationServer
@EnableFeignClients
@EnableDiscoveryClient
public class Oauth2Application extends WebMvcConfigurerAdapter{
	@Value("${oauth2.spring.datasource.url}")
	String url ;
	@Value("${oauth2.spring.datasource.username}")
	String userName;
	@Value("${oauth2.spring.datasource.password}")
	String password;
	@Bean
	public DataSource dataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setInitialSize(2);
		dataSource.setMaxActive(20);
		dataSource.setMinIdle(0);
		dataSource.setMaxWait(60000);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestOnBorrow(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setPoolPreparedStatements(false);
		return dataSource;
	}
	
	@Bean
	RestTemplate restTemplate (){
		return new RestTemplate();
	} 
	/**
	 * 添加事务管理
	 * */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
	}
	
}
