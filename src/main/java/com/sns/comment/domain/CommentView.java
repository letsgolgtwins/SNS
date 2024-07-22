package com.sns.comment.domain;

import com.sns.user.Entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CommentView { // 댓글 하나를 갖고 있는 클래스
	// 댓글 1개
	private Comment comment;
	
	// 그 댓글 1개를 쓴 사람
	private UserEntity user; 
}
