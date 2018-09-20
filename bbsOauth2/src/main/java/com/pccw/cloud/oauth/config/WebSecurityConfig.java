package com.pccw.cloud.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(10)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private Oauth2UserDetailsService oauth2UserDetailsService;
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 // 配置登陆页/login并允许访问
		http.formLogin().loginPage("/login").permitAll()
			// 登出页
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
			.and().authorizeRequests().antMatchers("/health", "/css/**").permitAll()
			// 其余所有请求全部需要鉴权认证
			.and().authorizeRequests().anyRequest().authenticated();
			//由于使用的是JWT，我们这里不需要csrf
//			.and().csrf().disable();
	}
	/**
	 * 用户验证
	 * */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(oauth2UserDetailsService);
//		auth.parentAuthenticationManager(authenticationManagerBean());
		auth.authenticationProvider(authenticationProvider());
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		// 设置userDetailsService
        provider.setUserDetailsService(oauth2UserDetailsService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(6));
        return provider;
        
	}
	
}
