package com.sns.timeline.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.BO.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.like.BO.LikeBO;
import com.sns.post.BO.PostBO;
import com.sns.post.Entity.PostEntity;
import com.sns.timeline.domain.CardView;
import com.sns.user.BO.UserBO;
import com.sns.user.Entity.UserEntity;

@Service
public class TimeLineBO {

	// PostBO 가져오기
	@Autowired
	private PostBO postBO;
	
	// userBO 가져오기
	@Autowired
	private UserBO userBO;
	
	// commentBO 가져오기
	@Autowired
	private CommentBO commentBO;
	
	// likeBO 가져오기
	@Autowired
	private LikeBO likeBO;
	
	// i: userId(로그인 된 사람 번호) / o: list<CardView>
	public List<CardView> generateCardViewList(Integer userId) { // view에 뿌리기 위해서 '가공'이되는 메소드명은 보통 get보단 generate를 앞에 붙인다.
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록을 가져온다. List<PostEntity> 
		List<PostEntity> postList = postBO.getPostEntityList();
		
		// 글 목록 반복문 순회
		// PostEntity 를 CardView 로 바꾸고 -> cardViewList에 넣기
		for (PostEntity post : postList) { // 향상된 for문
			CardView card = new CardView(); // 매번 새로 만들어져야 하므로 new 로 한다.
			
			// 글 setting
			card.setPost(post);
			
			// 글쓴이 setting 
			// int userId = post.getUserId(); // 이렇게도 얻을 수 있고
			// card.getPost().getUserId(); // 이렇게 얻을 수 있고
			UserEntity user = userBO.getUserEntityById(post.getUserId()); // 여기가 잘 이해가안됨
			card.setUser(user);	
		
			// 댓글 N개 setting
			List<CommentView> commentViewList = commentBO.generateCommentViewListByPostId(post.getId()); 
			// 댓글을 카드에 넣는다.
			card.setCommentList(commentViewList);
			
			// 좋아요 개수
			int likeCount = likeBO.getLikeCountByPostId(post.getId());
			card.setLikeCount(likeCount);
			
			// 좋아요 여부 
			card.setFilledLike(likeBO.filledLikeByPostIdAndUserId(post.getId(), userId));
			
			// !!!반드시 list에 넣는다.
			cardViewList.add(card);
		}
		
		return cardViewList;
	}
}
