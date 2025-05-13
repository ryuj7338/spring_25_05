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
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
public class UsrMemberController {

	
	@Autowired
	private MemberService memberService;


	// 액션 메서드

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(HttpServletRequest req, String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		
			
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
	public String doLogin(HttpServletRequest req, String loginId, String loginPw) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		if (Ut.isEmptyOrNull(loginId)) {
			return Ut.jsHistoryBack("F-1", "아이디를 입력하세요");
		}
		
		if (Ut.isEmptyOrNull(loginPw)) {
			return Ut.jsHistoryBack("F-1", "비밀번호를 입력하세요");
		}
		
		Member member = memberService.getMemberLoginId(loginId);
		
		if (member == null) {
			return Ut.jsHistoryBack("F-3", Ut.f("%s는(은) 없는 아이디입니다.", loginId));
		}
		
		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-4", "비밀번호가 일치하지 않습니다");
		}
		
		rq.login(member);
		
		return Ut.jsReplace("S-1", Ut.f("%s님 환영합니다.", member.getNickname()), "/");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		
		Rq rq = (Rq) req.getAttribute("rq");
						
		rq.logout();
		
		return Ut.jsReplace("S-1", "로그아웃 되었습니다.", "/");
	}
	
	
}
