package com.pccw.cloud.oauth.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用用户中心的服务获取用户信息
 * */
@FeignClient(value="USER",fallback=UserServiceHystric.class)
public interface UserService {
	@RequestMapping(value="/user/findUserPwdByAccount")
	public String findUserPwdByAccount(@RequestParam(value="userAccount") String userAccount);
}
