package com.sns.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.BO.CommentBO;
import com.sns.comment.domain.Comment;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {

	@Autowired
	private CommentBO commentBO;
	
	// 댓글 API 
	// 일단 getmapping으로 해서 확인할 것
	// /comment/create
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("comment") String comment, // 무슨 댓글을 쓸 건지? 댓글 내용
			@RequestParam("postId") int postId, // 어느글에 남길 건지? 글 번호
			HttpSession session) {
		
		// session에서 userId 가져오기
		Integer userId = (Integer) session.getAttribute("userId"); // 댓글 쓴이 = 현재 로그인한 유저의 고유 id(userId)
		
		Map<String, Object> result = new HashMap<>();
		// 로그인 여부 체크
		if (userId == null) { // 로그인 안된 상태
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		
		// 로그인 된 상태
		// 댓글 작성 - db에 insert
		commentBO.addComment(comment, postId, userId);
		
		// 응답 JSON
		result.put("code", 200);
		result.put("message", "성공");
		
		return result;
	}
	
}
