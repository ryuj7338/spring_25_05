package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ReactionService;
import com.example.demo.util.Ut;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrReactionController {

	private final UsrArticleController usrArticleController;
	
	@Autowired
	private Rq rq;
	
	@Autowired
	private ReactionService reactionService;
	
	UsrReactionController(UsrArticleController usrArticleController){
		this.usrArticleController = usrArticleController;
	}
	
	@RequestMapping("/usr/reaction/doLike")
	public String doLike(HttpServletRequest req, String relTypeCode, int relId, String replaceUri) {
		
		int usersReaction = reactionService.userCanReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		
		if(usersReaction == 1) {
			return Ut.jsHistoryBack("F-1", "이미 좋아요 눌렀습니다.");
		}
		
		ResultData reactionRd = reactionService.increaseReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		
		return Ut.jsReplace(reactionRd.getResultCode(), reactionRd.getMsg(), replaceUri);
	}
}
