package com.pccw.cloud.module.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModuleController {
	
	@ResponseBody
	@RequestMapping(value="/resourceTest")
	public String resourceTest() {
		return "resource test ok !";
	}
}
