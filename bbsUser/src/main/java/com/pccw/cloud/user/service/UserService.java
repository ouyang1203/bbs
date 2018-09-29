package com.pccw.cloud.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.cloud.user.logger.Logs;
import com.pccw.cloud.user.mapper.UserMapper;
@Service
public class UserService {
	private Logger log_ = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	User2Service user2Service;
	
	@Autowired
	UserMapper userMapper;
	
	public Integer login(String userAccount,String password){
		return userMapper.login(userAccount, password);
	}
	
	public String findUserPwdByAccount(String userAccount){
		return userMapper.findUserPwdByAccount(userAccount);
	}
	
	public void testLog() {
		log_.info("test service loging");
		user2Service.user2Index();
		try {
			throw new RuntimeException("测试异常打印");
		} catch (Exception e) {
			Logs.error(e);
		}
	}
}
