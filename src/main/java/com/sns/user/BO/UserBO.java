package com.sns.user.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.Entity.UserEntity;
import com.sns.user.Repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	// userId 중복확인 API
	// i: String userId o: UserEntity
	public UserEntity getUserEntityBuUserId(String UserId) {
		return userRepository.findByUserId(UserId);
	}
	
	// 회원가입 db에 insert
	public UserEntity addUserEntity(String userId, String password, String name, String email) {
		return userRepository.save(UserEntity.builder()
				.userId(userId)
				.password(password)
				.name(name)
				.email(email)
				.build()
				);
	}
	
	// 로그인 - db에서 단 건 select
	public UserEntity getUserEntityByUserIdAndPassword(String userId, String password) {
		return userRepository.findByUserIdAndPassword(userId, password);
	}
	
}
