package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;
	

	// 액션 메서드
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(HttpSession session, String title, String body) {
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		if(isLogined == false) {
			return ResultData.from("F-A", "로그인이 필요합니다.");
		}
		
		
		if (Ut.isEmptyOrNull(title)) {
			return ResultData.from("F-1", "제목을 입력하세요.");
		}
		
		if (Ut.isEmptyOrNull(body)) {
			return ResultData.from("F-2", "내용을 입력하세요.");
		}
		
		ResultData doWriteRd = articleService.writeArticle(loginedMemberId, title, body);
		
		int id = (int) doWriteRd.getData();
		
		Article article = articleService.getArticleId(id);
		
		return ResultData.newData(doWriteRd, article);
	}
	

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpSession session, int id) {
		
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		

		Article article = articleService.getArticleId(id);

		if (article == null) {
			return  ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}

		articleService.deleteArticle(id);

		return ResultData.from("S-1", Ut.f("%d번 글이 삭제되었습니다.", id));
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession session, int id, String title, String body) {
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		Article article = articleService.getArticleId(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}

		articleService.modifyArticle(id, title, body);

		return ResultData.from("S-1", Ut.f("%d번 글이 수정되었습니다.", id), article);
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {

		Article article = articleService.getArticleId(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글입니다.", id), article);
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {

		List<Article> articles = articleService.getArticles();
		
		return ResultData.from("S-1", "Article List", articles);
	}

}
