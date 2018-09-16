package com.pccw.cloud.oauth.service;

import org.springframework.stereotype.Component;

@Component
public class UserServiceHystric implements UserService{

	@Override
	public String findUserPwdByAccount(String userAccount) {
		return "";
	}

}
