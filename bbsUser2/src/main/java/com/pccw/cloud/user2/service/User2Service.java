package com.pccw.cloud.user2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class User2Service {
	private Logger log_ = LoggerFactory.getLogger(User2Service.class);
	
	public String testUser2Service() {
		String s = "user2 return";
		log_.info(s);
		return s;
	}
}
