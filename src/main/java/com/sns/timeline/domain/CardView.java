package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentView;
import com.sns.post.Entity.PostEntity;
import com.sns.user.Entity.UserEntity;

import lombok.Data;
import lombok.ToString;

// View용 객체
// 글 1개와 매핑됨
@ToString
@Data // getter setter
public class CardView { // View에 뿌리기위한 domain이란 의미
	// 글 1개 
	private PostEntity post;
	
	// 이 글을 쓴 사람(글쓴이)에 대한 정보
	private UserEntity user;
	
	// 댓글 n개 - list
	private List<CommentView> commentList;
	
	// 좋아요 n개
	
	// 좋아요를 내가 눌렀는 지 여부
}
