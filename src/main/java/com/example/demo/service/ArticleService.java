package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.vo.Article;

@Service
public class ArticleService {

	int lastArticleId;
	public List<Article> articles;
	
	
	public ArticleService() {
		
		articles = new ArrayList<>();
		lastArticleId = 0;
		
		testData();
	}
	
	private void testData() {
		
		for (int i = 1; i <= 10; i++) {
			String title = "탄지로";
			String body = "물의 호흡 제"+i+"형";
			
			writeArticle(title, body);
		}
		
	}
	
	public Article writeArticle(String title, String body) {
		
		int id = lastArticleId + 1;
		
		
		Article article = new Article(id, title, body);
		articles.add(article);
		
		lastArticleId++;
		
		return article;
	}
	
	
	public Article getArticleId(int id) {
		
		for (Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public void modifyArticle(int id, String title, String body) {
		Article article = getArticleId(id);
		
		article.setTitle(title);
		article.setBody(body);
		
	}

	public void deleteArticle(int id) {
		
		Article article = getArticleId(id);
		articles.remove(article);
		
	}
	
	
}
