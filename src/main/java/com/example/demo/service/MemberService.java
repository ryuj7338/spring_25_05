package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

//	서비스 메서드
	public MemberService(MemberRepository memberRepository) {

		this.memberRepository = memberRepository;

	}

	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {

		Member existsMember = getMemberLoginId(loginId);
			
		if(existsMember != null) {
			return ResultData.from("F-7", Ut.f("이미 사용중인 아이디(%s)입니다.", loginId));
		}


		existsMember = getMemberByNameAndEmail(name, email);
		
		if (existsMember != null) {
			return ResultData.from("F-8", Ut.f("이미 사용중인 이름과(%s)과 이메일(%s)입니다.", name, email));
		}
		
		memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberRepository.getLastInsertId();

		return ResultData.from("S-1","회원가입을 성공하였습니다.", id);
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public Member getMemberLoginId(String loginId) {
		return memberRepository.getMemberLoginId(loginId);
	}

	public Member getMemberId(int id) {

		return memberRepository.getMemberId(id);
	}

}
