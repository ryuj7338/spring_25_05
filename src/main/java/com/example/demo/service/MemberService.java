package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	
//	서비스 메서드
	public MemberService(MemberRepository memberRepository) {

		this.memberRepository = memberRepository;
		
	}
	
	
	public int doJoin(String loginId, String loginPw, String name) {
		
		memberRepository.doJoin(loginId, loginPw, name);
		
		return memberRepository.getLastInsertId();
	}
	
	public Member getMemberId(int id) {

		return memberRepository.getMemberId(id);
	}

}
