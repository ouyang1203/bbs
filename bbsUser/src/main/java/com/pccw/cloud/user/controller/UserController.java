package com.pccw.cloud.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pccw.cloud.user.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	private Logger log_ = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	@ResponseBody
	@RequestMapping(value="/login")
	public String login(@RequestParam String userAccount,@RequestParam String password){
		String msg = "登录失败,请重试!";
		Integer count = userService.login(userAccount, password);
		if(count!=null&&count>0){
			msg = "欢迎"+userAccount+"登录";
		}
		return msg;
	}
	@ResponseBody
	@RequestMapping(value="/index")
	public String index(){
		log_.info("index method invoke");
		return "user index page";
	}
	/**
	 * 此方法用于Oauth2认证服务器获取用户信息
	 * */
	@ResponseBody
	@RequestMapping(value="/findUser")
	public String findUser(@RequestParam String userAccount,@RequestParam String password){
		Integer count = userService.login(userAccount, password);
		if(count!=null&&count>0){
			return userAccount;
		}
		return "";
	}
	/**
	 * 此方法用于Oauth2认证服务器获取用户信息
	 * */
	@ResponseBody
	@RequestMapping(value="/findUserPwdByAccount")
	public String findUserPwdByAccount(@RequestParam String userAccount){
		return userService.findUserPwdByAccount(userAccount);
	}
}
