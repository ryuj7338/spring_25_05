package com.example.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Member;

@Mapper
public interface MemberRepository {
	
//	@Insert ("INSERT INTO `member` SET regDate = NOW(), loginId = #{loginId}, loginPw = #{loginPw}, `name` = #{name}")
	public int doJoin (String loginId, String loginPw, String name);

	public Member getMemberId(int id);
	
	public int getLastInsertId();
	
	public Member getMemberLoginId(String loginId);
}
