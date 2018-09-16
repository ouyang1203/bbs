package com.pccw.cloud.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.cloud.user.mapper.UserMapper;
@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public Integer login(String userAccount,String password){
		return userMapper.login(userAccount, password);
	}
	
	public String findUserPwdByAccount(String userAccount){
		return userMapper.findUserPwdByAccount(userAccount);
	}
}
