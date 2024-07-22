package com.sns.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.comment.BO.CommentBO;
import com.sns.comment.domain.Comment;

@RequestMapping("/comment")
@Controller
public class CommentController {

	@Autowired
	private CommentBO commentBO;

	// 댓글 뿌리기 API
	// i: / o:여러행
	@GetMapping("/comment-view")
	public List<Comment> commentView(@RequestParam("postId") int postId, Model model) {
		// db에서 댓글 select
		List<Comment> commentList = commentBO.getCommentListByPostId(postId);

		// model에 담기
		model.addAttribute("commentInfo", commentList);

		return "음";
	}
}
