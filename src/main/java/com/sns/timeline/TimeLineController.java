package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.post.BO.PostBO;
import com.sns.post.Entity.PostEntity;

import jakarta.servlet.http.HttpSession;

@Controller
public class TimeLineController {

	// 다른 컨트롤러가 남의 BO를 부른다
	@Autowired
	private PostBO postBO;
	
	// 타임라인 페이지 and db에서 컨텐츠 가져오기 select
	// http://localhost:8080/timeline/timeline-view
	@GetMapping("/timeline/timeline-view")
	public String timeLineView(/*필요없음 HttpSession session,*/ Model model) {
		// 로그인 여부 확인 - 필요없다.
//		Integer userId = (Integer) session.getAttribute("userId");
//		if (userId == null) { // 로그인이 안되어 있는 경우
//			// 로그인 페이지로 이동
//			return "redirect:/user/sign-in-view";
//		}
		
		// db에서 select (JPA)
		List<PostEntity> postList = postBO.getPostEntityList();
		
		// model에 담기
		model.addAttribute("postInfo", postList);
		
		return "timeline/timeline";
	}
}
