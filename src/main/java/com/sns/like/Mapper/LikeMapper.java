package com.sns.like.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

	// 좋아요 누름 - db에 insert
	public int insertLikeByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId
			);
	
	// 좋아요 해제 - db에서 delete
	public int deleteLikeByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId
			);
	
	// 특정 사용자의 좋아요 눌렀는지 조회 - db에서 select
	public int selectLikeCountByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId
			);
	
	// 좋아요 모두 조회 - db에서 select
	public int selectLikeCountByPostId(int postId); 
}
