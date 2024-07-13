package com.sns.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.user.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	// db에서 UserId 조회
	public UserEntity findByUserId(String userId);
}
