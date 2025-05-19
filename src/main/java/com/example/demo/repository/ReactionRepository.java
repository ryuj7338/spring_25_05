package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReactionRepository {

	public int getSumReaction(int memberId, String relTypeCode, int relId);

	public int addLikeReaction(int memberId, String relTypeCode, int relId);
	
	public int addDislikeReaction(int memberId, String relTypeCode, int relId);
	
	public void deleteReaction(int memberId, String relTypeCode, int relId);
}
