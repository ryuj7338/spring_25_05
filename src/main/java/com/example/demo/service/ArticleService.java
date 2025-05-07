package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.vo.Article;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

//	서비스 메서드
	public ArticleService(ArticleRepository articleRepository) {

		this.articleRepository = articleRepository;
		testData();

	}

	private void testData() {

		for (int i = 1; i <= 10; i++) {
			String title = "탄지로";
			String body = "물의 호흡 제" + i + "형";

			articleRepository.writeArticle(title, body);

		}

	}

	public Article writeArticle(String title, String body) {

		return articleRepository.writeArticle(title, body);
	}

	public void deleteArticle(int id) {

		articleRepository.deleteArticle(id);

	}

	public void modifyArticle(int id, String title, String body) {

		articleRepository.modifyArticle(id, title, body);

	}

	public Article getArticleId(int id) {

		return articleRepository.getArticleId(id);
	}

	public List<Article> getArticles() {

		return articleRepository.getArticles();
	}

}
