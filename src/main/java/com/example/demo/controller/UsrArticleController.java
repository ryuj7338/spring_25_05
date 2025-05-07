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
	
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		
		int id = lastArticleId + 1;
		
		
		
		Article article = new Article(id, title, body);
		articles.add(article);
		
		lastArticleId++;
		
		return article;
	}
	
	private void testData() {
		for (int i = 1; i <= 10; i++) {
			String title = "탄지로";
			String body = "물의 호흡 제"+i+"형";
			
			doAdd(title, body);
		}
		
	}
//	
//	@RequestMapping("/usr/article/doDelete")
//	@ResponseBody
//	public Article doDelete() {
//		
//		Article foundArticle = null;
//		
//		for(Article foundArtcle : articles) {
//			if(article.getID() == id) {
//				foundArticle = article;
//			}
//		}
//		
//		
//		return article;
//	}
	

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		return articles;
	}
	
	
	
	
}

