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
	
	
	int lastArticleId = 10;
	List<Article> articles;
	
	public UsrArticleController() {
		articles = new ArrayList<>();
		lastArticleId = 10;
		
	}
	
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		
		int id = lastArticleId + 1;
		
		
		testData();
		Article article = new Article(id, title, body);
		articles.add(article);
		
		lastArticleId++;
		
		return article;
	}
	

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		return articles;
	}
	
	
	private void testData() {
		articles.add(new Article(1,"탄지로","물의 호흡 제 1형"));
		articles.add(new Article(2,"탄지로","물의 호흡 제 2형"));
		articles.add(new Article(3,"탄지로","물의 호흡 제 3형"));
		articles.add(new Article(4,"탄지로","물의 호흡 제 4형"));
		articles.add(new Article(5,"탄지로","물의 호흡 제 5형"));
		articles.add(new Article(6,"탄지로","물의 호흡 제 6형"));
		articles.add(new Article(7,"탄지로","물의 호흡 제 7형"));
		articles.add(new Article(8,"탄지로","물의 호흡 제 8형"));
		articles.add(new Article(9,"탄지로","물의 호흡 제 9형"));
		articles.add(new Article(10,"탄지로","물의 호흡 제 10형"));
		
	}
	
	
}

