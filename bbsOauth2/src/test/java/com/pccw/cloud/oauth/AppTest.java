package com.pccw.cloud.oauth;


import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import com.sun.jersey.core.util.Base64;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	
	private static Logger log_ = LoggerFactory.getLogger(AppTest.class);
    /**
     * Rigorous Test :-)
     */
    public void test(){
    	String token ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzY4NzQyMjksInVzZXJfbmFtZSI6InRlc3QiLCJhdXRob3JpdGllcyI6WyJhZG1pbiJdLCJqdGkiOiJmOWM2MjAxYi1iMWRiLTQxNDMtODRmZi0wOThjOGZiMzk2ZjQiLCJjbGllbnRfaWQiOiJjbGllbnQiLCJzY29wZSI6WyJhcHAiXX0.W30kGGIk74fluT9nCgGrGTQoFpBWEVCBKp9NxLYkxsk1L8AFJbRHFUOZSGpN-RbA9rIla5uHT-JVXn5WCKDfywlw-4VpIeoeSKDXU-BfjPLgLpizTF1JJsQ-EBw83kl2-qx9NoQsb-MBTgsTM6WsB4msCz4T75P5PQuWLKercuHm9MNN2CX5Pr_1ZVuaV4Xml6Jr3kOjb82cxcxp0r-02aVG1wrstZkqrQxlj_X_AFEIOj4VeKA3H5cCIO4nWES40fJF9ggHlIvioY7wrNSYOWkGsSnYA2Z_x_m9a4aTuY7L8BHj3zMENwMRTA6E_DmE9-hqnHxiao8hyCrXKah0Cg";
    	Jwt jwt = JwtHelper.decode(token); 
    	log_.info("jwt token info is {}",jwt.toString());
    }
	
    public static void main(String[] args) {
		String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsienV1bCJdLCJ1c2VyX25hbWUiOiJ0ZXN0MSIsInNjb3BlIjpbImFwcCJdLCJleHAiOjE1MzcyODM3MjEsImF1dGhvcml0aWVzIjpbImFkbWluIiwib3RoZXIiXSwianRpIjoiMTk1MmE4NGEtMDk4Ny00Nzg5LTg5YmMtYzQ5NWZjYTU4MDI5IiwiY2xpZW50X2lkIjoienV1bCJ9.GrivdCE7OFR_JvAc50B6GPUj99wMbMGWMFdaK6-LBZSd_00rgrONwQZuvJGTsfXmTtNaG_V03gfWXBu2lQyPeBmY6nCazfKp1buqn6EBdRCZdnsale__MTaykNWwaFaAf7cJKFKwQm4OtDDJ2YyblK1yWa_msCQfuBkT-I5wOsAiXrjG6r7suBiW951WFZZsDS1wd0N0gkq_yyo7slzcT4h5xQMEAAfVrEngb-hVghd8Je8Ys3CTYxXwFKGlsjwEHpjszbyxqTNuymX8RMFzSpRMbJKZBF-WS_Z8YJwol3-JA94wemWdN0B1TLp2vNxsfa3XC4sbihMaoWATuAhyZg";
		Jwt jwt = JwtHelper.decode(token); 
		long millis = 1537283721;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		Date date = cal.getTime();
		log_.info("jwt token info is {}",jwt.toString());
		log_.info("token exp at {}",date.toLocaleString());
	}
    
	/**获取公钥*/
	public void publicKeyTest(){
		Resource resource = new ClassPathResource("ouyang_key.jks");
		KeyPair keyPair = new KeyStoreKeyFactory(resource, "oyh1203oy".toCharArray()).getKeyPair("ouyang_key");
		RSAPublicKey key = (RSAPublicKey) keyPair.getPublic();
		log_.info("key is {}",key);
		String verifyerKey = "-----BEGIN PUBLIC KEY-----\n"+ new String(Base64.encode(key.getEncoded()))
		+ "\n-----END PUBLIC KEY-----";
		log_.info("verifyerKey is {}",verifyerKey);
	}
}
