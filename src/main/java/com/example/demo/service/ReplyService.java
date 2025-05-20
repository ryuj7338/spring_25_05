package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.repository.ReplyRepository;
import com.example.demo.vo.ResultData;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private ArticleService articleServivce;
	
	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public ResultData usersReply(int loginedMemberId, String body, int id) {


		if (loginedMemberId == 0) {
			return ResultData.from("F-L", "로그인이 필요합니다.");
		}

		
		
		return null;
	}
	
	
	
}
