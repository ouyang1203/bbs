package com.pccw.cloud.oauth.config;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pccw.cloud.oauth.service.UserService;
@Service
public class Oauth2UserDetailsService implements UserDetailsService{
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String name)throws UsernameNotFoundException {
		User user = mockUser(name);
		return user;
	}

	private User mockUser(String name) {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("admin"));
		if("test1".equals(name)) {
			//用于测试不同用户权限
			authorities.add(new SimpleGrantedAuthority("other"));
		}
		//查询用户中心的用户密码
		String pwd = userService.findUserPwdByAccount(name);
		User user = new User(name,pwd,authorities);
		return user;
	}
}
