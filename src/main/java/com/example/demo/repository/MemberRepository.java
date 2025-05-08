package com.example.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Member;

@Mapper
public interface MemberRepository {
	
//	@Insert ("INSERT INTO `member` SET regDate = NOW(), loginId = #{loginId}, loginPw = #{loginPw}, `name` = #{name}")
	public int doJoin (String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	public Member getMemberId(int id);
	
	public int getLastInsertId();
	
	public Member getMemberLoginId(String loginId);

	public Member getMemberByNameAndEmail(String name, String email);
}
