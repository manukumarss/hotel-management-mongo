package com.excelsoft.hotelmanagement.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@SuppressWarnings("serial")
@Document(collection = "user")

public class User extends BaseEntity{
	
//	private static final long serialVersionUID = 1L;
//	
//	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "USER_ID", nullable = false, unique = true)
//	private long id;
	
	
	private String userFirstName;
	
	private String userLastName;
	
	private String userName;
	
	private String password;
	
	private String roleType;
	
	private String phoneNumber;
	
	private String emailId;
	
	private Integer isDeleted;

	private Timestamp createdDate;
	
	private Timestamp lastModifiedDate;
	
	private String existingPassword;

	private String newPassword;

	private String confirmPassword;
	
	public User(){
		
	}
	
	public User(Timestamp lastModifiedDate, Timestamp createdDate, Integer isDeleted, String emailId, String phoneNumber, String userFirstName, String userLastName, String userName, String password, String roleType, String existingPassword){
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userName = userName;
		this.password = password;
		this.roleType = roleType;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.isDeleted = isDeleted;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.existingPassword = existingPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}
	
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public String getExistingPassword() {
		return existingPassword;
	}

	public void setExistingPassword(String existingPassword) {
		this.existingPassword = existingPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp formattedDate) {
		this.createdDate = formattedDate;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
		
}
