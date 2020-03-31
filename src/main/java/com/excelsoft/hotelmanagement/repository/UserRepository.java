package com.excelsoft.hotelmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excelsoft.hotelmanagement.entity.User;


public interface UserRepository extends CrudRepository<User, String> {

	User save(Optional<User> usr);

	User findBypassword(String password);

	//@Query(value = "SELECT * FROM USER_DETAILS WHERE EMAIL_ID = :emailId and password = :password", nativeQuery = true)
	//@Query("{'EMAIL_ID' : ?0, 'password' : ?1}")
	//User checkUserNameAndPasswordExistOrNot(@Param("emailId") String emailId, @Param("password") String password);
//	@Query("{'EMAIL_ID' : ?0, 'password' : ?1}")
//	User findByuserNameAndpassword(@Param("emailId") String emailId, @Param("password") String password);

}
