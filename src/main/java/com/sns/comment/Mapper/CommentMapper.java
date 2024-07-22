package com.sns.comment.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.comment.domain.Comment;

@Mapper
public interface CommentMapper {

	// 댓글 쓰기 - db에 insert
	public int insertComment(
			@Param("comment") String comment, 
			@Param("postId") int postId, 
			@Param("userId") int userId
			);
	
	// i) 댓글 불러오기 - db에서 select
	public List<Comment> selectCommentList();
	
	// ii) 댓글 불러오기 - db에서 select
	public List<Comment> selectCommentListByPostId(int postId);
}
