package com.sns.comment.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.Mapper.CommentMapper;
import com.sns.comment.domain.Comment;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	// 댓글 쓰기 - db에 insert
	public void addComment(String comment, int postId, int userId) {
		commentMapper.insertComment(comment, postId, userId);
	}
	
	// 댓글 가져오기 - db에서 select
	public List<Comment> getCommentListByPostId(int postId) {
		return commentMapper.selectCommentListByPostId(postId);
	}
	
}
