package com.excelsoft.hotelmanagement.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Constants {
	
	private Constants() {	
	}

	public static Integer ISDELETED_TRUE = 1;
	public static Integer ISDELETED_FALSE = 0;
	public static String ADMIN_ROLE = "admin";
	public static String OWNER_ROLE = "owner";
	public static String USER_ROLE = "user";
	public static String USER_CREATED = "user created successfully";
	public static String USER_ALREADY_EXISTS = "user already registred";
	public static String VALID_USER = "valid user";
	public static String INVALID_USER = "Not a valid user";
	public static String PHONE_NUMBER_REGEX = "[0-9]{10}";
	public static String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	public static String getOTP() {
		Random r = new Random();
		List<Integer> codes = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			int x = r.nextInt(9999);
			while (codes.contains(x))
				x = r.nextInt(9999);
			codes.add(x);
		}

		return String.format("%04d", codes.get(0));
	}
}
