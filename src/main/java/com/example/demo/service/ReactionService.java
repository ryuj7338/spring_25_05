package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReactionRepository;
import com.example.demo.vo.ResultData;

@Service
public class ReactionService {

	@Autowired
	private ReactionRepository reactionRepository;

	@Autowired
	private ArticleService articleService;

	public ReactionService(ReactionRepository reactionRepository) {
		this.reactionRepository = reactionRepository;
	}

	public ResultData usersReaction(int loginedMemberId, String relTypeCode, int relId) {

//		로그인 x
		if (loginedMemberId == 0) {
			return ResultData.from("F-L", "로그인이 필요합니다.");
		}

		int sumReactionByMemberId = reactionRepository.getSumReaction(loginedMemberId, relTypeCode, relId);

		if (sumReactionByMemberId != 0) {
			return ResultData.from("F-1", "추천 불가능", "sumReactionByMemberId", sumReactionByMemberId);
		}

		return ResultData.from("S-1", "추천 가능", "sumReactionByMemberId", sumReactionByMemberId);
	}

	public ResultData addLikeReaction(int loginedMemberId, String relTypeCode, int relId) {

		int affectedRow = reactionRepository.addLikeReaction(loginedMemberId, relTypeCode, relId);

		if (affectedRow != 1) {
			return ResultData.from("F-1", "좋아요 실패");
		}

		switch (relTypeCode) {
		case "article":
			articleService.increaseLikeReaction(relId);
			break;
		}

		return ResultData.from("S-1", "좋아요:)");
	}

	public ResultData addDislikeReaction(int loginedMemberId, String relTypeCode, int relId) {

		int affectedRow = reactionRepository.addDislikeReaction(loginedMemberId, relTypeCode, relId);

		if (affectedRow != 1) {
			return ResultData.from("F-1", "싫어요 실패");
		}

		switch (relTypeCode) {
		case "article":
			articleService.increaseDislikeReaction(relId);
			break;
		}

		return ResultData.from("S-1", "싫어요:(");
	}

	public ResultData deleteLikeReaction(int loginedMemberId, String relTypeCode, int relId) {

		reactionRepository.deleteReaction(loginedMemberId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.decreaseLikeReaction(relId);
			break;
		}

		return ResultData.from("S-1", "좋아요 취소");
	}

	public ResultData deleteDislikeReaction(int loginedMemberId, String relTypeCode, int relId) {

		reactionRepository.deleteReaction(loginedMemberId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.decreaseDislikeReaction(relId);
			break;
		}

		return ResultData.from("S-1", "싫어요 취소");
	}

	public boolean isAlreadyAddLikeRp(int memberId, int relId, String reltypeCode) {
		
		int getPointTypeCodeByMemberId = reactionRepository.getSumReaction(memberId, reltypeCode, relId);
		
		if(getPointTypeCodeByMemberId > 0) {
			return true;
		}
		
		return false;
	}

	public Object isAlreadyAddDislikeRp(int memberId, int relId, String reltypeCode) {
	
	int getPointTypeCodeByMemberId = reactionRepository.getSumReaction(memberId, reltypeCode, relId);
		
		if(getPointTypeCodeByMemberId < 0) {
			return true;
		}
		
		return false;
	}

}