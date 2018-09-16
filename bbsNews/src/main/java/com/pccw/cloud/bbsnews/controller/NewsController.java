package com.pccw.cloud.bbsnews.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
	@ResponseBody
	@RequestMapping(value="testToken")
	public String testToken(@RequestParam(value="token")String token) {
		return "";
	}
	@ResponseBody
	@RequestMapping(value="testIndex")
	public String testIndex() {
		return "index";
	}
}
