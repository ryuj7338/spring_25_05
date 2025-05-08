package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.util.Ut;
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

		Member existsMember = getMemberLoginId(loginId);
		System.out.println("existsMember: " + existsMember);

		if (existsMember != null) {
			return -1;
		}

		memberRepository.doJoin(loginId, loginPw, name);

		return memberRepository.getLastInsertId();
	}

	private Member getMemberLoginId(String loginId) {
		return memberRepository.getMemberLoginId(loginId);
	}

	public Member getMemberId(int id) {

		return memberRepository.getMemberId(id);
	}

}
