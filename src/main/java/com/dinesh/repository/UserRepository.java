package com.dinesh.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dinesh.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUserEmailAndIsActive(String emailId, byte isActive);
	
	List<User> findByUserTypeOrderByUserIdAsc(byte isActive);

	User findByUserId(long userId);
	
	User findByUserIdAndIsActive(long userId, byte isActive);
	
	List<User> findByIsActiveAndUserTypeOrderByUserIdAsc(byte isActive, byte userType);
}
