package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;



@Controller
public class UsrMemberController {

	
	@Autowired
	private MemberService memberService;


	// 액션 메서드

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Member doJoin(String loginId, String loginPw, String name) {

		int id = memberService.doJoin(loginId, loginPw, name);
		
		Member member = memberService.getMemberId(id);
		
		return member;
	}

	
}
