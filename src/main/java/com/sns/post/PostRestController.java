package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.BO.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;

	// 글 및 사진(파일) 업로드
	// /post/create
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam(value = "content", required = false) String content,
			@RequestParam("file") MultipartFile file,
			HttpSession session) {
		
		// session에서 꺼내기
		Integer userId = (Integer) session.getAttribute("userId"); // 그 유저의 고유 번호 숫자
		String userLoginId = (String) session.getAttribute("userLoginId"); // 그 유저의 로그인 아이디
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 403);  // 비로그인 상태
			result.put("signIn_request_message", "로그인을 해주세요.");
			return result;
		}
		
		// db에 글 및 사진(파일) insert
		postBO.addPost(userId, userLoginId, content, file);
		
		result.put("code", 200);
		result.put("result", "성공");
		return result;
		
	}
	
}
