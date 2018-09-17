package com.pccw.cloud.bbsnews.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
	@ResponseBody
	@RequestMapping(value="testToken")
	public String testToken(@RequestParam(value="token")String token) {
		return "";
	}
	@ResponseBody
	@RequestMapping(value="testIndex")
	public String testIndex(Principal principal,OAuth2Authentication auth2Authentication ) {
		String name = principal.getName();
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationDetails userDetails =  (OAuth2AuthenticationDetails)auth.getDetails();
		List<GrantedAuthority> roles = (List<GrantedAuthority>) auth.getAuthorities();
		List<GrantedAuthority> roles2 = (List<GrantedAuthority>)auth2Authentication.getAuthorities();
		//获取当前用户权限信息
		String token = userDetails.getTokenValue();
		return "index:"+name+"current Authorities is :"+roles.toString()+"\t current token is:"+token;
	}
	@PreAuthorize("hasAuthority('admin')")
	@ResponseBody
	@RequestMapping(value="testAdminRole")
	public String testAdminRole() {
		return "admin view";
	}
	
	@PreAuthorize("hasAuthority('other')")
	@ResponseBody
	@RequestMapping(value="testOtherRole")
	public String testOtherRole() {
		return "other view";
	}
}
