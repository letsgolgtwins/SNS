package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.BO.UserBO;
import com.sns.user.Entity.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	// db에서 중복검사
	// /user/is-duplicated
	@GetMapping("/is-duplicated")
	public Map<String, Object> isDuplicated(
			@RequestParam("userId") String userId) {
		// db에서 조회
		UserEntity user = userBO.getUserEntityBuUserId(userId);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (user != null) { // 즉, id가 중복이라는 거
			result.put("code", 200);
			result.put("message", "is_duplicate");
		} else { // id가 중복이 아니라는 거. 즉 사용할 수 있다는 셈
			result.put("code", 500);
			result.put("message", "not_duplicate");
		}
		return result;
	}
	
	// 회원가입 - db에 insert
	// /user/sign-up
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		// MD5 암호화 
		String hashedPassword = EncryptUtils.md5(password);
		
		// db에 insert
		UserEntity user = userBO.addUserEntity(userId, hashedPassword, name, email);
		
		// 응답 JSON
		Map<String, Object> map = new HashMap<>();
		if (user != null) { // 회원가입 성공
			map.put("code", 200);
			map.put("message", "성공");
		} else {
			map.put("code", 500);
			map.put("message", "실패");
		}
		return map;
	}
	
	// 로그인 - db에서 단 건 select
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		// hashing 처리
		String hashedPassword = EncryptUtils.md5(password);
		
		// db에서 select
		UserEntity user = userBO.getUserEntityByUserIdAndPassword(userId, hashedPassword);
		
		// 로그인 유지, 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (user != null) { // 즉, 로그인 완료
			HttpSession session = request.getSession();
			session.setAttribute("userLoginId", user.getId());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userName", user.getName());
			
			result.put("code", 200);
			result.put("result", "성공");
		} else { // 즉, 로그인 실패
			result.put("code", 403);
			result.put("error_message", "로그인에 실패하였습니다.");
		}
		return result;
	}
	
}
