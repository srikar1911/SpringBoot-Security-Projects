package com.nt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
	public void configure(HttpSecurity http)throws Exception{
		http.authorizeHttpRequests().antMatchers("/","/login","/home").permitAll()
									.anyRequest().authenticated()
									.and().formLogin()
									.and().oauth2Login();
	
	
	
	}
	
}
