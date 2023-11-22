package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BootSecurityProj01App1Application {

	//we are not getting encoder throng autoConfiguration, so we have to create a bean
	@Bean
	public BCryptPasswordEncoder createBCryprpasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(BootSecurityProj01App1Application.class, args);
	}

}
