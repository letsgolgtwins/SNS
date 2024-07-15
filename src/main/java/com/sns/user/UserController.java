package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {

	// 회원가입 페이지로 이동
	// http://localhost:8080/user/sign-up-view
	@GetMapping("/sign-up-view")
	public String signUpView() {
		return "user/signUp";
	}

	// 로그인 페이지로 이동
	// http://localhost:8080/user/sign-in-view
	@GetMapping("/sign-in-view")
	public String signInView() {
		return "user/signIn";
	}
	
	// 로그아웃 
	// /user/sign-out
	@RequestMapping("/sign-out")
	public String signOut(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		return "redirect:/user/sign-in-view";
	}
}
