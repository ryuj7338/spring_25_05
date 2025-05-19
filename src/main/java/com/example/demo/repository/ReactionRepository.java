package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReactionRepository {

	int getSumReaction(int loginedMemberId, String relTypeCode, int relId);
}
