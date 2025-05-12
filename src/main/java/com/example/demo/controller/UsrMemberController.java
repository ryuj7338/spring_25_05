package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;



@Controller
public class UsrMemberController {

	
	@Autowired
	private MemberService memberService;


	// 액션 메서드

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(HttpSession session, String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		
		boolean isLogined = false;
		
		if(session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		
		if (isLogined) {
			return ResultData.from("F-A", "이미 로그인되어 있습니다.");
		}
		
		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", "아이디를 입력하세요.");
		}
		
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-1", "비밀번호를 입력하세요.");
		}
		
		if (Ut.isEmptyOrNull(name)) {
			return ResultData.from("F-1", "이름을 입력하세요.");
		}
		
		if (Ut.isEmptyOrNull(nickname)) {
			return ResultData.from("F-1", "닉네임을 입력하세요.");
		}
		
		if (Ut.isEmptyOrNull(cellphoneNum)) {
			return ResultData.from("F-1", "전화번호를 입력하세요.");
		}
		
		if (Ut.isEmptyOrNull(email)) {
			return ResultData.from("F-1", "이메일을 입력하세요.");
		}
		

		ResultData doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		if (doJoinRd.isFail()) {
			return doJoinRd;
		}
		
		Member member = memberService.getMemberId((int) doJoinRd.getData1());
		
		return ResultData.newData(doJoinRd, "새로 생성된 member", member);
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "/usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData<Member> doLogin(HttpSession session, String loginId, String loginPw) {
		
		
		boolean isLogined = false;
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		
		if (isLogined) {
			return ResultData.from("F-A", "이미 로그인이 되어 있습니다.");
		}
		
		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", "아이디를 입력하세요");
		}
		
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-1", "비밀번호를 입력하세요");
		}
		
		Member member = memberService.getMemberLoginId(loginId);
		
		if (member == null) {
			return ResultData.from("F-3", Ut.f("%s는(은) 없는 아이디입니다.", loginId));
		}
		
		if (member.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다");
		}
		
		session.setAttribute("loginedMemberId", member.getId());
		
		return ResultData.from("loginedMemberId", Ut.f("S-1", "%s님 환영합니다.", member.getNickname()), "로그인한 회원", member);
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData<Integer> doLogout(HttpSession session, String loginId, String loginPw) {
		
		
		boolean isLogined = false;
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		
		if (!isLogined) {
			return ResultData.from("F-A", "이미 로그아웃 되어 있습니다.");
		}
		
				
		session.removeAttribute("loginedMemberId");
		
		return ResultData.from("S-1", Ut.f("로그아웃 되었습니다."));
	}
	
	
}
