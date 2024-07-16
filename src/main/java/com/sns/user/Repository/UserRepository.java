package com.sns.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.user.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	// db에서 UserId 조회 (회원가입 中 userId 중복확인)
	public UserEntity findByUserId(String userId);
	
	// db에서 단 건 조회 - select (로그인 中 userId와 password가 일치하는 정보 있는지 select)
	public UserEntity findByUserIdAndPassword(String userId, String password);
}
