package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserInfo;


public interface IUserInfoRepository extends JpaRepository<UserInfo, Integer>{
 
   //single record so optional type 
	//in the repo class we have predefined methods to get recod based on the id value
	//but here we need the record(user details) based on the username, so we need a custom method/query 	
	public Optional<UserInfo> findByUname(String uname);
	
	
	
		
}
 