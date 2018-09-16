package com.pccw.cloud.oauth.controller;

import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2Controller {
	private Logger log_ = LoggerFactory.getLogger(Oauth2Controller.class);
	
	/**使用httpclient发起post请求*/
	@ResponseBody
	@RequestMapping(value="/postOauth2Token")
	public String postOauth2TokenByClientAndCode(@RequestParam String client_id,@RequestParam String code,@RequestParam String client_secret,@RequestParam String redirect_uri){
		try {
			HttpClient client = new DefaultHttpClient();
			// 设置超时时间
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

			String postUrl = "http://"+client_id+":"+client_secret+"@localhost:1010/uaa/oauth/token?grant_type=authorization_code";
			postUrl += "&code="+code+"&client_id="+client_id+"&client_secret="+client_secret+"&redirect_uri="+redirect_uri;
			HttpPost post = new HttpPost(postUrl);
			post.setHeader("Content-type", "application/json; charset=utf-8");
			post.setHeader("Connection", "Close");
	        
	        HttpResponse response =  client.execute(post);
	        // 检验返回码
	        int statusCode = response.getStatusLine().getStatusCode();
	        log_.info("statusCode is {}",statusCode);
	        String str = "";
	        if(HttpStatus.SC_OK==statusCode){
	        	org.apache.http.HttpEntity res = response.getEntity();
	        	str = EntityUtils.toString(res, Charset.forName("UTF-8"));
	        }
	        log_.info("post result is {}",str);
	        return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 将JWT的Token解析为JSON字符串
	 * */
	public String decodeToken(String info) {
    	Jwt jwt = JwtHelper.decode(info); 
    	String str = jwt.toString();
    	log_.info("jwt token info is {}",str);
    	return str;
	} 
	
}
