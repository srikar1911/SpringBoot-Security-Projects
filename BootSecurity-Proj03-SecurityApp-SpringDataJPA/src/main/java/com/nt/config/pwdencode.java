package com.nt.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class pwdencode {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd1 = encoder.encode("firstuser");
		String pwd2 = encoder.encode("seconduser");
		String pwd3 = encoder.encode("thirduser");
		
		System.out.println(pwd1);
		System.out.println(pwd2);
		System.out.println(pwd3);
	}

}
