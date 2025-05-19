package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReactionRepository;

@Service
public class ReactionService {

	@Autowired
	private ReactionRepository reactionRepository;
	
	public ReactionService(ReactionRepository reactionRepository) {
		this.reactionRepository = reactionRepository;
	}
	
	public int userCanReaction(int loginedMemberId, String relTypeCode, int relId) {
		
//		로그인 x
		if(loginedMemberId == 0) {
			return -2;
		}
		
		return reactionRepository.getSumReaction(loginedMemberId, relTypeCode, relId);
	}
}
