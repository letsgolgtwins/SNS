package com.sns.like.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

	// 특정 사용자의 좋아요 눌렀는지 조회(toggle의 조회 목적) - db에서 select
	public int selectLikeCountByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId
			);

	// 좋아요 누름(toggle에서 조회한 다음 insert하는 경우) - db에 insert
	public int insertLikeByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId
			);
	
	// 좋아요 해제(toggle에서 조회한 다음 delete하는 경우) - db에서 delete
	public int deleteLikeByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId
			);
	
	// 특정글의 좋아요 개수 db에서 select
	public int selectLikeCountByPostId(int postId);
	
	// 카운트 쿼리 하나로 합치기
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId, 
			@Param("userId") Integer userId
			);
}
