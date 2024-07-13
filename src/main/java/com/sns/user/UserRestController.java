package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.user.BO.UserBO;
import com.sns.user.Entity.UserEntity;

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
}
