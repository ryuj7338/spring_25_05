package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;



@Controller
public class UsrMemberController {

	
	@Autowired
	private MemberService memberService;


	// 액션 메서드

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		
		
		if (Ut.isEmptyOrNull(loginId)) {
			return "아이디를 입력하세요";
		}
		
		if (Ut.isEmptyOrNull(loginPw)) {
			return "비밀번호를 입력하세요";
		}
		
		if (Ut.isEmptyOrNull(name)) {
			return "이름을 입력하세요";
		}
		
		if (Ut.isEmptyOrNull(nickname)) {
			return "이름을 입력하세요";
		}
		
		if (Ut.isEmptyOrNull(cellphoneNum)) {
			return "이름을 입력하세요";
		}
		
		if (Ut.isEmptyOrNull(email)) {
			return "이름을 입력하세요";
		}
		

		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		if(id == -1) {
			return Ut.f("이미 사용중인 아이디(%s)입니다.", loginId);
		}
		
		if(id == -2) {
			return Ut.f("이미 사용중인 이름과(%s) 이메일(%s)입니다.", name, email);
		}
		
		Member member = memberService.getMemberId(id);
		
		return member;
	}

	
}
