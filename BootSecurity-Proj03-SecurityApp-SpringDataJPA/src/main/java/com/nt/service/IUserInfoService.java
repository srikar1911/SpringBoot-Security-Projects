package com.nt.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nt.entity.UserInfo;

public interface IUserInfoService extends UserDetailsService {
	
	public String register(UserInfo user);
	

}
