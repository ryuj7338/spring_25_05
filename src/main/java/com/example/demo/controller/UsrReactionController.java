package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.service.ReactionService;
import com.example.demo.util.Ut;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrReactionController {

	@Autowired
	private Rq rq;

	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/reaction/doLike")
	@ResponseBody
	public ResultData doLike(String relTypeCode, int relId, String replaceUri) {

		ResultData usersReactionRd = reactionService.usersReaction(rq.getLoginedMemberId(), relTypeCode, relId);

		int usersReaction = (int) usersReactionRd.getData1();

		if (usersReaction == 1) {
			ResultData rd = reactionService.deleteLikeReaction(rq.getLoginedMemberId(), relTypeCode, relId);

			int like = articleService.getLike(relId);
			int dislike = articleService.getDislike(relId);
			
			return ResultData.from("S-1", "좋아요 취소", "like", like, "dislike", dislike);
			
		} else if (usersReaction == -1) {
			ResultData rd = reactionService.deleteDislikeReaction(rq.getLoginedMemberId(), relTypeCode, relId);
			rd = reactionService.addLikeReaction(rq.getLoginedMemberId(), relTypeCode, relId);

			int like = articleService.getLike(relId);
			int dislike = articleService.getDislike(relId);
			return ResultData.from("S-2", "싫어요 취소", "like", like, "dislike", dislike);
		}

		ResultData reactionRd = reactionService.addLikeReaction(rq.getLoginedMemberId(), relTypeCode, relId);

		if (reactionRd.isFail()) {
			return ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg());
		}

		int like = articleService.getLike(relId);
		int dislike = articleService.getDislike(relId);
		return ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg(), "like", like, "dislike", dislike);
	}

	@RequestMapping("/usr/reaction/doDislike")
	@ResponseBody
	public ResultData doDislike(String relTypeCode, int relId, String replaceUri) {

		ResultData usersReactionRd = reactionService.usersReaction(rq.getLoginedMemberId(), relTypeCode, relId);

		int usersReaction = (int) usersReactionRd.getData1();

		if (usersReaction == -1) {
			ResultData rd = reactionService.deleteDislikeReaction(rq.getLoginedMemberId(), relTypeCode, relId);
			
			int like = articleService.getLike(relId);
			int dislike = articleService.getDislike(relId);
			
			return ResultData.from("S-1", "싫어요 취소", "like", like, "dislike", dislike);
			
		} else if (usersReaction == 1) {
			ResultData rd = reactionService.deleteLikeReaction(rq.getLoginedMemberId(), relTypeCode, relId);
			rd = reactionService.addDislikeReaction(rq.getLoginedMemberId(), relTypeCode, relId);

			int like = articleService.getLike(relId);
			int dislike = articleService.getDislike(relId);
			return ResultData.from("S-2", "좋아요 취소", "like", like, "dislike", dislike);
		}

		ResultData reactionRd = reactionService.addDislikeReaction(rq.getLoginedMemberId(), relTypeCode, relId);

		if (reactionRd.isFail()) {
			return ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg());
		}

		int like = articleService.getLike(relId);
		int dislike = articleService.getDislike(relId);
		
		return ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg(), "like", like, "dislike", dislike);
	}
}
