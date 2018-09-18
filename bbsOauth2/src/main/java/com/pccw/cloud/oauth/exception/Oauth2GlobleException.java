package com.pccw.cloud.oauth.exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 定义全局异常处理
 * */
@RestController
@SessionAttributes("authorizationRequest")  
public class Oauth2GlobleException {
	
	@ResponseBody
	@RequestMapping(value="/login?error")
	public String loginException() {
		return "login error ,password is wrong or user name not exists,please retry";
	}
}
