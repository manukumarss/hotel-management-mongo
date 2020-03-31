package com.excelsoft.hotelmanagement.service;

import java.util.List;
import java.util.Optional;

import com.excelsoft.hotelmanagement.entity.User;

public interface UserService {
	List<User> getUsers();

	User createUser(User user);

	Optional<User> getById(String id);
	
	public User update(User user, String l);
	
	public User updatePartially(User user, String password);
//	
//	User findBypassword(String password); 
//
//	boolean authenticateByUserName(String userName);
//
	User findBypassword(String emailId);
//	
//	Integer updatePassword(String userName,String password);
}
