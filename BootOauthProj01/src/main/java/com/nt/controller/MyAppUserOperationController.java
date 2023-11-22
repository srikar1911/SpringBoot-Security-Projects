package com.nt.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAppUserOperationController {

	@GetMapping("/home")
	public String ShowHoemPage() {
		return "<h1> Welcome to MyAPP.com HomePage</h1>";
		
	}
	
	@GetMapping("/after")
	public String afterLoginPage() {
		return "<h1> Page afetr loginig  </h1>";
	}
	
	
	@GetMapping("/user")
	public Authentication showUserDetails(Principal principal) {
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
	
	
	
	
	
}
