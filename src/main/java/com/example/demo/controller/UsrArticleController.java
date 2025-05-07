package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {
	
	
	int lastArticleId;
	List<Article> articles;
	
	public UsrArticleController() {
		articles = new ArrayList<>();
		lastArticleId = 0;
		testData();
	}
	
	// 서비스 메서드
	private void testData() {
		for (int i = 1; i <= 10; i++) {
			String title = "탄지로";
			String body = "물의 호흡 제"+i+"형";
			
			writeArticle(title, body);
		}
		
	}
	
	private Article getArticleId(int id) {
		for (Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	// 액션 메서드
	@RequestMapping("/usr/article/writeArticle")
	@ResponseBody
	public Article writeArticle(String title, String body) {
		
		int id = lastArticleId + 1;
		
		
		
		Article article = new Article(id, title, body);
		articles.add(article);
		
		lastArticleId++;
		
		return article;
	}
	
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = getArticleId(id);
		
		
		if (article == null) {
			return id+"번 글은 없습니다.";
		}
		
		articles.remove(id);
		
		
		return id+"번 글이 삭제되었습니다.";
	}
	
	public String doModify(int id, String title, String body) {
		
		Article article = getArticleId(id);
		
		
		if (article == null) {
			return id+"번 글은 없습니다.";
		}
		
		
		
		article.setTitle(title);
		article.setBody(body);		
		
		return id+"번 글이 수정되었습니다." + article;
	}


	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		return articles;
	}
	
	
	
	
}

