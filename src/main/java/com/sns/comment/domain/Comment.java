package com.sns.comment.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Comment {

	// 필드
	private int id;
	private int commentId;
	private int postId;
	private String comment;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
