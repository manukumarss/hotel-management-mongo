package com.excelsoft.hotelmanagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excelsoft.hotelmanagement.entity.User;
import com.excelsoft.hotelmanagement.repository.UserRepository;
import com.excelsoft.hotelmanagement.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepo;

	/*
	 * public UserServiceImpl(UserRepository userRepo) { this.userRepo = userRepo; }
	 */
	@Override
	public List<User> getUsers() {
		LOGGER.info("Started Hotel-Management getUsers Service :");

		return (List<User>) this.userRepo.findAll();
	}

	@Override
	public User createUser(User user) {
		LOGGER.info("Started Hotel-Management create user Service :");
		return this.userRepo.save(user);
	}

	@Override
	public Optional<User> getById(String id) {
		LOGGER.info("Started Hotel-Management find user getUserById Service :");

		return (Optional<User>) this.userRepo.findById(id);
	}
	
	public User update(User user, String l) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}
	
	public User updatePartially(User user, String password) {
		// TODO Auto-generated method stub
		User usr = this.userRepo.findBypassword(password);
		usr.setPassword(user.getNewPassword());
		return this.userRepo.save(usr);
	}

//	@Override
//	public boolean authenticateByUserName(String userName) {
//		LOGGER.info("Started Hotel-Management find user authenticateByUserName Service :");
//
//		Optional<User> userOpt = this.userRepo.findByUserName(userName);
//		return userOpt.isPresent();
//	}
//
	@Override
	public User findBypassword(String password) {
		LOGGER.info("Started Hotel-Management find user findBypassword Service :");

		return this.userRepo.findBypassword(password);
	}
//
//	@Override
//	public User checkUserNameAndPasswordExistOrNot(String userName, String password) {
//		LOGGER.info("Started Hotel-Management find user username and password Service :");
//
//		return this.userRepo.findByuserNameAndpassword(userName, password);
//
//	}
//
//	@Override
//	public Integer updatePassword(String userName, String password) {
//		LOGGER.info("Started Hotel-Management update user password Service :");
//
//		return this.userRepo.changePassword(userName, password);
//
//	}
}
