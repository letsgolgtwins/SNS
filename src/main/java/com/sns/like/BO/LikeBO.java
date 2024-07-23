package com.sns.like.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.Mapper.LikeMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	// toggle 메소드 
	// i: userId, postId / o: X
	public void likeToggle(int postId, HttpSession session) { 
  		Integer userId = (Integer) session.getAttribute("userId");
		// 조회
		int likeCount = likeMapper.selectLikeCountByPostIdAndUserId(postId, postId);
		
		// 조회해보고 그 여부에 따라 => 삭제 or 추가
		if (likeCount == 1) { // 로그인된 유저가 이미 좋아요 누름 => 삭제
			likeMapper.deleteLikeByPostIdAndUserId(postId, userId);
		} else { // 좋아요 안누른 상태 => 추가
			likeMapper.insertLikeByPostIdAndUserId(postId, userId);
		}
	}
	
	// 그 글에 대한 좋아요 개수 파악
	public int likeCount(int postId) {
		// 좋아요 개수 조회
		return likeMapper.selectLikeCountByPostId(postId);
	}
	
}
