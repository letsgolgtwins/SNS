package com.sns.comment.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Comment {

	// 필드
	private int id; // pk
	private int commentId; // 댓글을 다는 사람 번호 
	private int postId; // 댓글이 달리는 글의 번호
	private String comment;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
