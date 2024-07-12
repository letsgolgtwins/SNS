package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private 
	
	// db에서 중복검사
	// /user/is-duplicated
	@GetMapping("/is-duplicated")
	public Map<String, Object> isDuplicated() {
		//
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		result.put("", result)
	}
}
