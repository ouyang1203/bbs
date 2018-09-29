package com.pccw.cloud.user2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pccw.cloud.user2.service.User2Service;

@RestController
public class User2Controller {
	private Logger log_ = LoggerFactory.getLogger(User2Controller.class);
	@Autowired
	User2Service user2Service;
	
	@ResponseBody
	@RequestMapping(value="/user2Index")
	public String user2Index(){
		log_.info("index method invoke ");
		user2Service.testUser2Service();
		return "user2 index page ";
	}
}
