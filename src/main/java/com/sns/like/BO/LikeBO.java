package com.sns.like.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.Mapper.LikeMapper;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	// toggle 메소드 
	// i: userId, postId / o: X
	public void likeToggle(int postId, int userId) { 
		// 조회
		int count = likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);
		
		// 조회해보고 그 여부에 따라 => 삭제 or 추가
		if (count > 0) { // 로그인된 유저가 이미 좋아요 누름 => 삭제
			likeMapper.deleteLikeByPostIdAndUserId(postId, userId);
		} else { // 좋아요 안누른 상태 => 추가
			likeMapper.insertLikeByPostIdAndUserId(postId, userId);
		}
	}
	
	// 수정 
	public int getLikeCountByPostIdAndUserId(int postId, int userId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);
	}
	
	// 수정
	// i: postId / o: int (좋아요 개수)
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	// 좋아요 채울지 여부 
	// i: postId(필수), userId(로그인/비로그인)
	// o: boolean(채울지 여부)
	public boolean filledLikeByPostIdAndUserId(int postId, Integer userId) {
		// 비로그인이면 DB 조회 없이 하트 채우지 않음
		if (userId == null) {
			return false;
		}
		
		// 로그인이면 1. 행이있으면(1) true 2. 없으면(0) false
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) == 1 ? true : false;
	}
	
}
