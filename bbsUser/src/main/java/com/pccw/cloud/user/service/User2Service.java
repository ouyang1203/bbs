package com.pccw.cloud.user.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="USER2")
public interface User2Service {
	@RequestMapping(value="/user2Index")
	public String user2Index();
}
