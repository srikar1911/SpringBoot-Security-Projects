package com.nt.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
 
	@Autowired
	private DataSource ds; 
	
	private UserDetailsService userDetailsService;
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//create InMemoryDB as Authenticator	
//		auth.inMemoryAuthentication().withUser("Raja").password("{noop}firstuser").roles("CLERK");
//		auth.inMemoryAuthentication().withUser("Suresh").password("{noop}seconduser").roles("MANAGER");
//		auth.inMemoryAuthentication().withUser("ramesh").password("{noop}thirduser").roles("MANAGER","CELRK");

//		generate encoded passwords using BCryptencoder
//		$2a$10$789lIftVwC7e4eNnbSwileLgbuNvvCyyKabp435OaGay3CVJlM2xC
//		$2a$10$TIcm5fNTrWj7Hc6Z.Z718uADICBONt9Knbt/zDLyX.o.CKmhHRIEi
//		$2a$10$f4y5Vm/ELHjr3z781Xe57OoAjMC2SWR3hAZNmR3QIZjeTPbzlvZ6e

//		using INMemoryDB authenticator using encoded pwds 
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("Raja").password("$2a$10$789lIftVwC7e4eNnbSwileLgbuNvvCyyKabp435OaGay3CVJlM2xC").roles("CLERK");
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("Suresh").password("$2a$10$TIcm5fNTrWj7Hc6Z.Z718uADICBONt9Knbt/zDLyX.o.CKmhHRIEi").roles("MANAGER");
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("ramesh").password("$2a$10$f4y5Vm/ELHjr3z781Xe57OoAjMC2SWR3hAZNmR3QIZjeTPbzlvZ6e").roles("MANAGER","CELRK");
		
		
//		authentication and authorisation using db table 
		
		auth.jdbcAuthentication().dataSource(ds).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("SELECT UNAME,pwd, status FROM USERS WHERE UNAME=?") //for authentication
				.authoritiesByUsernameQuery("SELECT role, UNAME FROM USER_ROLES WHERE UNAME=?"); //for authorisation
		
////		
//		auth.userDetailsService(userDetailsService);
		
		
		
	}	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/bank").permitAll()	
								
								.antMatchers("/user/register", "user/showLogin").permitAll()
								.antMatchers("bank/offers").authenticated()
								.antMatchers("bank/balance").hasAnyAuthority("CLERK","MANAGER")
								.antMatchers("bank/loan").hasAuthority("MANAGER") 
								.anyRequest().authenticated()
				
								//.and().httpBasic()
								.and().formLogin()
									.defaultSuccessUrl("/bank/home",true)
									//.loginPage("/user/showLogin")
									//.loginProcessingUrl("/login")
									.failureUrl("/user/showLogin?error")
								.and().rememberMe()							 
								.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/signout")).logoutSuccessUrl("/user/showLogin?logout")

								.and().exceptionHandling().accessDeniedPage("/denied")
								.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);
		
								
	}
}
