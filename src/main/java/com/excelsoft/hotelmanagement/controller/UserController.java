package com.excelsoft.hotelmanagement.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.excelsoft.hotelmanagement.common.Constants;
import com.excelsoft.hotelmanagement.dto.UserRequestDTO;
import com.excelsoft.hotelmanagement.dto.UserResponseDTO;
import com.excelsoft.hotelmanagement.entity.User;
import com.excelsoft.hotelmanagement.service.UserService;

import net.jodah.expiringmap.ExpiringMap;

import com.excelsoft.hotelmanagement.repository.UserRepository;

@RestController
@RequestMapping(value={"/user"})
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;

//	private Map<String, String> otpmap = ExpiringMap.builder().expiration(10, TimeUnit.MINUTES).build();
//
//	public Map<String, String> getOtpmap() {
		return otpmap;
	}

	public void addOtpmap(String key, String otp) {
		otpmap.put(key, otp);
	}
	
	@GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
	public String get(){
		return "Please give url as Spring-Boot-Rest/user/get";
		
	}
	
	@PostMapping(value="/create",headers="Accept=application/json")
	 public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
	     System.out.println("Creating User "+user.getUserName());
	     int otpRandom = new Random().nextInt((9999 - 100) + 1) + 10;
	     user.setPassword(String.valueOf(otpRandom));
	     User creadtedUserDetails = new User();
	     creadtedUserDetails = userService.createUser(user);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	     return new ResponseEntity<User>(creadtedUserDetails, headers, HttpStatus.CREATED);
	 }

	@GetMapping(value="/get", headers="Accept=application/json")
	 public List<User> getAllUser() {	 
	  List<User> tasks=userService.getUsers();
	  return tasks;
	
	 }
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> getUserById(@PathVariable("id") String id) {
        System.out.println("Fetching User with id " + id);
        Optional<User> user = userService.getById(id);
        if (user == null) {
            return null;
        }
        return user;
    }
	
	@PutMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> userChangePassword(@RequestBody User user)
	{
//		System.out.println("sd");
//		Optional<User> usr = userService.getById(user.getId());
//	if (user==null) {
//		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
//	}
	userService.updatePartially(user, user.getExistingPassword());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
//	@PostMapping(value="/login",headers="Accept=application/json")
//	public ResponseEntity<User> userLogin(@RequestBody User user) {
//		LOGGER.info("Started Hotel-Management Login user :");
//		User userDetails = new User();
//		//UserResponseDTO userResponseDTO = new UserResponseDTO();
////		if (user.getUserName() == null || user.getUserName().isEmpty()) {
////			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
////		} else if (user.getPassword() == null || user.getPassword().isEmpty()) {
////			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
////		} else {
//
//			try {
//				userDetails = this.userService.checkUserNameAndPasswordExistOrNot(user.getUserName(), user.getPassword());
//			} catch (Exception e) {
//				LOGGER.info("Hotel-Management Login Exception :", e);
//			}
//			if (userDetails != null) {
//				//user.setUserId(userDetails.getId());
//				user.setUserName(userDetails.getUserName());
//				user.setPassword(userDetails.getPassword());
//				user.setRoleType(userDetails.getRoleType());
//				//user.setMessage(Constants.VALID_USER);
//				LOGGER.info("End Hotel-Management Login user :");
//				return ResponseEntity.ok(user);
//			} else {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			}
//	//	}
//
//	}
	
	
//	@PostMapping(value="/login",headers="Accept=application/json")
//	public ResponseEntity<User> userLogin(@RequestBody User user) {
//		LOGGER.info("Started Hotel-Management Login user :");
//		User userDetails = new User();
//		userDetails = this.userService.findBypassword(user.getPassword());
//			if (userDetails != null) {
//				LOGGER.info("End Hotel-Management Login user :");
//				return ResponseEntity.ok(user);
//			} else {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			}
//	}
    
	
//	@PostMapping(value="/changePassword",headers="Accept=application/json")
//	public ResponseEntity<User> userChangePassword(@RequestBody User user) {
//		LOGGER.info("Started Hotel-Management user changePassword:");
//		Integer isUserPasswordUpdated = 0;
//		//User user = new User();
//		 if (user.getNewPassword() == null || user.getNewPassword().isEmpty()) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else if (user.getExistingPassword() == user.getNewPassword()
//				|| !user.getNewPassword().matches(user.getConfirmPassword())) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else {
//			try {
//				
//				user = this.userService
//						.findBypassword(user.getExistingPassword());
//				
//				if(user != null)
//				{
//					isUserPasswordUpdated = this.userService.updatePassword(user.getUserName(),
//							user.getNewPassword());
//				} else {
//					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//				}
//			} catch (Exception e) {
//				LOGGER.info("Hotel-Management Login Exception :", e);
//			}
//			if (isUserPasswordUpdated == 1) {
//				LOGGER.info("End Hotel-Management user changePassword:");
//				return ResponseEntity.status(HttpStatus.OK).build();
//			} else {
//				LOGGER.info("End Hotel-Management user changePassword:");
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			}
//		}
//	}
	
//	@GetMapping("/getAllUsersDetails")
//	public List<User> getUsers() {
//		return this.userService.getUsers();
//	}
//
//	@PostMapping("/createNewUser")
//	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
//		LOGGER.info("Started Hotel-Management createUser :");
//		User user = new User();
//		Boolean checkUserExistsOrNot;
//		User creadtedUserDetails = new User();
//		UserResponseDTO userResponseDTO = new UserResponseDTO();
//
//		if (userRequestDTO.getUserFirstName() == null || userRequestDTO.getUserFirstName().isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else if (userRequestDTO.getUserLastName() == null || userRequestDTO.getUserLastName().isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else if (userRequestDTO.getUserEmail() == null || userRequestDTO.getUserEmail().isEmpty()
//				|| userRequestDTO.getUserEmail().length() <= 0
//				|| !userRequestDTO.getUserEmail().matches(Constants.EMAIL_REGEX)) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else if (userRequestDTO.getPhoneNumber() == null || userRequestDTO.getPhoneNumber().isEmpty()
//				|| userRequestDTO.getPhoneNumber().length() <= 0
//				|| !userRequestDTO.getPhoneNumber().matches(Constants.PHONE_NUMBER_REGEX)) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else {
//			try {
//				checkUserExistsOrNot = this.userService.authenticateByUserName(userRequestDTO.getUserEmail());
//				if (checkUserExistsOrNot != null && !checkUserExistsOrNot){
//				user.setUserFirstName(userRequestDTO.getUserFirstName());
//				user.setUserLastName(userRequestDTO.getUserLastName());
//				user.setUserName(userRequestDTO.getUserEmail());
//				int otpRandom = new Random().nextInt((9999 - 100) + 1) + 10;
//				
//				// otp session  handling pending.
//				addOtpmap(userRequestDTO.getUserEmail(), String.valueOf(otpRandom));
//				
//				
//				user.setPassword(String.valueOf(otpRandom));
//				user.setRoleType(Constants.USER_ROLE);
//				user.setPhoneNumber(userRequestDTO.getPhoneNumber());
//				user.setEmailId(userRequestDTO.getUserEmail());
//				user.setIsDeleted(Constants.ISDELETED_FALSE);
//				Timestamp currentDateAndTime = new Timestamp(System.currentTimeMillis());
//				user.setCreatedDate(currentDateAndTime);
//				user.setLastModifiedDate(currentDateAndTime);
//				creadtedUserDetails = userService.createUser(user);
//				if (creadtedUserDetails != null) {
//					userResponseDTO.setUserId(creadtedUserDetails.getId());
//					userResponseDTO.setUserName(creadtedUserDetails.getUserName());
//					userResponseDTO.setPassword(creadtedUserDetails.getPassword());
//					userResponseDTO.setRoleType(creadtedUserDetails.getRoleType());
//					userResponseDTO.setMessage(Constants.USER_CREATED);
//				}
//				
//				}else {
//					userResponseDTO.setMessage(Constants.USER_ALREADY_EXISTS);
//					return new ResponseEntity(userResponseDTO, HttpStatus.NOT_ACCEPTABLE);
//				}
//				
//			} catch (Exception e) {
//				LOGGER.info("Hotel-Management createUser Exception :", e);
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//			}
//		}
//		LOGGER.info("End Hotel-Management createUser :");
//		return ResponseEntity.ok(userResponseDTO);
//	}
//
//	@GetMapping("{id}")
//	public User getUserByIds(@PathVariable long id) {
//		
//		return this.userService.getUserById(id);
//	}
//
//	@PostMapping("/login")
//	public ResponseEntity<UserResponseDTO> userLogin(@RequestBody UserResponseDTO userDTO) {
//		LOGGER.info("Started Hotel-Management Login user :");
//		User userDetails = new User();
//		UserResponseDTO userResponseDTO = new UserResponseDTO();
//		if (userDTO.getUserName() == null || userDTO.getUserName().isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else {
//
//			try {
//				userDetails = this.userService.checkUserNameAndPasswordExistOrNot(userDTO.getUserName(), userDTO.getPassword());
//			} catch (Exception e) {
//				LOGGER.info("Hotel-Management Login Exception :", e);
//			}
//			if (userDetails != null) {
//				userResponseDTO.setUserId(userDetails.getId());
//				userResponseDTO.setUserName(userDetails.getUserName());
//				userResponseDTO.setPassword(userDetails.getPassword());
//				userResponseDTO.setRoleType(userDetails.getRoleType());
//				userResponseDTO.setMessage(Constants.VALID_USER);
//				LOGGER.info("End Hotel-Management Login user :");
//				return ResponseEntity.ok(userResponseDTO);
//			} else {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			}
//		}
//
//	}

//	@PostMapping("/changePassword")
//	public ResponseEntity<UserResponseDTO> userChangePassword(@RequestBody UserRequestDTO userRequestDTO) {
//		LOGGER.info("Started Hotel-Management user changePassword:");
//		Integer isUserPasswordUpdated = 0;
//		User user = new User();
//		 if (userRequestDTO.getNewPassword() == null || userRequestDTO.getNewPassword().isEmpty()) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else if (userRequestDTO.getConfirmPassword() == null || userRequestDTO.getConfirmPassword().isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else if (userRequestDTO.getExistingPassword() == userRequestDTO.getNewPassword()
//				|| !userRequestDTO.getNewPassword().matches(userRequestDTO.getConfirmPassword())) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else {
//			try {
//				
//				user = this.userService
//						.findBypassword(userRequestDTO.getExistingPassword());
//				
//				if(user != null)
//				{
//					isUserPasswordUpdated = this.userService.updatePassword(user.getUserName(),
//								userRequestDTO.getNewPassword());
//				} else {
//					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//				}
//			} catch (Exception e) {
//				LOGGER.info("Hotel-Management Login Exception :", e);
//			}
//			if (isUserPasswordUpdated == 1) {
//				LOGGER.info("End Hotel-Management user changePassword:");
//				return ResponseEntity.status(HttpStatus.OK).build();
//			} else {
//				LOGGER.info("End Hotel-Management user changePassword:");
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			}
//		}
//	}
//
//	@PostMapping("/forgotPassword")
//	public ResponseEntity<Boolean> userForgotPassword(@RequestParam String userName) {
//		LOGGER.info("Started Hotel-Management user ForgotPassword:");
//		int otpRandom = 0;
//		Integer userpasswordUpdate = 0;
//		if (userName == null || userName.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else {
//			try {
//				Boolean checkUserNameExistsORNot = this.userService.authenticateByUserName(userName);
//
//				if (checkUserNameExistsORNot!= null && checkUserNameExistsORNot) {
//					otpRandom = new Random().nextInt((9999 - 100) + 1) + 10;
//					addOtpmap(userName, String.valueOf(otpRandom));
//					// send to mobile or emailId pending
//					userpasswordUpdate = this.userService.updatePassword(userName, String.valueOf(otpRandom));
//				}else {
//					
//					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//				}
//			} catch (Exception e) {
//				LOGGER.info("Hotel-Management Login Exception :", e);
//			}
//			if (userpasswordUpdate == 1) {
//				LOGGER.info("End Hotel-Management user ForgotPassword:");
//				return ResponseEntity.ok(Boolean.TRUE);
//			} else {
//				LOGGER.info("End Hotel-Management user ForgotPassword:");
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			}
//		}
//	}

}
