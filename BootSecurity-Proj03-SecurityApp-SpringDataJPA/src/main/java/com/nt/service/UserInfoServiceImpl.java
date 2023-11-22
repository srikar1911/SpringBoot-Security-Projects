package com.nt.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nt.entity.UserInfo;
import com.nt.repository.IUserInfoRepository;

@Service("userService")
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private IUserInfoRepository userrepo;
	
	//userDetailsService is predefined interface
	//user class is a predefined class
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// get mode/entity class object 
		Optional<UserInfo> opt = userrepo.findByUname(username);
		
		if(opt.isEmpty())throw new IllegalArgumentException("invalid inputs");
			
			UserInfo info = opt.get();
		//get roles from model class object
		Set<String> roles = info.getRoles();
		
		//convert Set<String> roles to Set<SGA> object (SGA- simple grant authority)
		Set<GrantedAuthority> sgaRoles = new HashSet();
		
		for(String role:roles) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
			sgaRoles.add(authority);
		}
		
		//converting model class object into user object
		User user = new User(info.getUname(), info.getPwd(), sgaRoles);
		return user ;
	}

	@Override
	public String register(UserInfo user) {
		userrepo.save(user);
		return "user registered";
	}
	
	
} 
