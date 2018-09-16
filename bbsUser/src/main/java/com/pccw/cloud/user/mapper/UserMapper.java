package com.pccw.cloud.user.mapper;

public interface UserMapper {
	public Integer login(String userAccount,String password);
	
	public String findUserPwdByAccount(String userAccount);
}
