package com.nt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.entity.UserInfo;
import com.nt.service.IUserInfoService;

@Controller
@RequestMapping("/user")
public class UserOperationController {
		
	@Autowired
	private IUserInfoService userService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/register")
	public String showUserResistrationForm(@ModelAttribute("userinfo") UserInfo info){
		
		return "user_register";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("userinfo")UserInfo user, Map<String,Object>map) {
		//encoding the pwd
		String enPwd = encoder.encode(user.getPwd());
		//modifying the user pwd to encodedpwd
		user.setPwd(enPwd);
		//
		String msg = userService.register(user);
		//keeping msg in modelAttribute
		map.put("resultMsg", msg);
		
		return "user_register_sucess";
	}
	
	@GetMapping("/showLogin")
	public String showLoginPage() {
		return "custom_login";
		
	}
	
	
}
