package com.sns.comment.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.Mapper.CommentMapper;
import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.user.BO.UserBO;
import com.sns.user.Entity.UserEntity;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;

	// commentBO 가 댓글쓴이를 가져오기 위해
	@Autowired
	private UserBO userBO;
	
	// 댓글 쓰기 - db에 insert
	public void addComment(String comment, int postId, int userId) {
		commentMapper.insertComment(comment, postId, userId);
	}
	
	// i) 댓글 가져오기 - db에서 select
	public List<Comment> getCommentListByPostId(int postId) {
		return commentMapper.selectCommentListByPostId(postId);
	}
	
	// ii) 댓글 가져오기 - db에서 select
	// i: 글번호 / o: List<commentView>
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 댓글들 가져옴
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		// 반복문 순회 => Comment => CommentView => list에 담음
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 1개
			commentView.setComment(comment);
			
			// 댓글쓴이
			UserEntity user = userBO.getUserEntityById(comment.getCommentId()); // 댓글쓴이 번호(commentId)를 가져와서
			commentView.setUser(user);
			
			// !!!!! list에 넣기 반드시!
			commentViewList.add(commentView);
		}
		
		return commentViewList;
	}
	
}
