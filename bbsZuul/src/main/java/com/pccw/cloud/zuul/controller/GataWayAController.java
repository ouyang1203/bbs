package com.pccw.cloud.zuul.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GataWayAController {
private Logger log_ = LoggerFactory.getLogger(GataWayAController.class);
	
	@ResponseBody
	@RequestMapping(value="/gateIndex")
	public String zuulIndex(OAuth2Authentication auth2Authentication) {
		OAuth2AuthenticationDetails userDetails =  (OAuth2AuthenticationDetails)auth2Authentication.getDetails();
		List<GrantedAuthority> roles = (List<GrantedAuthority>)auth2Authentication.getAuthorities();
		//获取当前用户权限信息
		String token = userDetails.getTokenValue();
		String name = auth2Authentication.getName();
		log_.info("current token is {} \n current user is {} \t current role is {}",token,name,roles);
		return "zuul index page ,current user is :"+name;
	}
}
