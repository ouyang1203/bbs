package com.pccw.cloud.oauth;


import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

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
		String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzY5NzA4MjEsInVzZXJfbmFtZSI6InRlc3QiLCJhdXRob3JpdGllcyI6WyJhZG1pbiJdLCJqdGkiOiI0YjVlNTRhMS00YTZkLTQxM2UtYmZhMy02ZDQ2MzJjNTljZjQiLCJjbGllbnRfaWQiOiJjbGllbnQiLCJzY29wZSI6WyJhcHAiXX0.ULVQoCmmq0RLGuoKcTT9D_st8YTsaos_Wew6aEjYjKfkTgCCvLP0lzzySwlKRQreOc__-zfmswYADSBJTQ7FVZUnnlERg6RaxGA7KlZori3N1bvs32Ix7oB49AovlqRWRFC4jdsuV15krGTu7HfexYXz7D60BOlY2WoNnzDAv_W7tMJIlCsC3MXRuEmiLSm6wIQ7M5khL_LJniUSVQuesDUy-JrgwqZ9Je-z2cQt7-t7w456qG66BVuFI3M8rmdPX8hiLpFO6jRomRAA0c_Xruy_EEFQuBiYjqzO3cIqkrATjx9LgaxfunIKzeSxkq6H8WF8Xl4Bx2K6kAmMSlCanQ";
		Jwt jwt = JwtHelper.decode(token); 
		log_.info("jwt token info is {}",jwt.toString());
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
