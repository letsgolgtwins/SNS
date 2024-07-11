package com.sns.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.post.mapper.PostMapper;
// 이곳은 프로그램이 잘 돌아가는지 테스트를 하기 위한 공간입니다.
@Controller
public class TestController {

	@Autowired
	private PostMapper postMapper;
	
	// 1. @ResponseBody return String 되는지 확인
	// http://localhost:8080/test1
	@ResponseBody
	@GetMapping("/test1")
	public String test1() {
		return "<h2>hello World!</h2>";
	}
	
	// 2. @ResponseBody return Map => JSON 되는지 확인
	// http://localhost:8080/test2
	@ResponseBody
	@GetMapping("/test2")
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("오지환", 10);
		map.put("홍창기", 51);
		map.put("임찬규", 1);
		return map;
	}
	
	// 3. 타임리프 html => application.yml 확인
	// http://localhost:8080/test3
	@GetMapping("/test3")
	public String test3() {
		return "Test/test";
	}
	
	// 4. MyBatis DB 연동 되는지 확인 (단, SnsApplication에서 Enable 어노테이션 지우고 할 것)
	// http://localhost:8080/test4
	@ResponseBody
	@GetMapping("/test4")
	public List<Map<String, Object>> test4() {
		return postMapper.selectPostList();
	}
}
