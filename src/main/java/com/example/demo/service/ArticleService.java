package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

//	서비스 메서드
	public ArticleService(ArticleRepository articleRepository) {

		this.articleRepository = articleRepository;

	}

	public ResultData writeArticle(int memberId, String title, String body, String boardId) {

		articleRepository.writeArticle(memberId, title, body, boardId);

		int id = articleRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%d번 글이 등록되었습니다.", id), "등록된 게시글 id", id);
	}

	public void deleteArticle(int id) {

		articleRepository.deleteArticle(id);

	}

	public void modifyArticle(int id, String title, String body) {

		articleRepository.modifyArticle(id, title, body);

	}

	public ResultData userCanModify(int loginedMemberId, Article article) {

		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-A", Ut.f("%d번 게시글에 대한 수정 권한이 없습니다.", article.getId()));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글이 수정되었습니다.", article.getId()));
	}

	public ResultData userCanDelete(int loginedMemberId, Article article) {

		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-A", Ut.f("%d번  게시글에 대한 삭제 권한이 없습니다.", article.getId()));
		}
		return ResultData.from("S-1", Ut.f("%d번 게시글이 삭제되었습니다.", article.getId()));
	}

	public Article getForPrintArticle(int loginedMemberId, int id) {

		Article article = articleRepository.getForPrintArticle(id);

		controlForPrintData(loginedMemberId, article);

		return article;
	}

	public void controlForPrintData(int loginedMemberId, Article article) {

		if (article == null) {

			return;

		}

		ResultData userCanModifyRd = userCanModify(loginedMemberId, article);

		article.setUserCanModify(userCanModifyRd.isSuccess());

		ResultData userDeleteRd = userCanDelete(loginedMemberId, article);

		article.setUserCanDelete(userDeleteRd.isSuccess());
	}

	public Article getArticleId(int id) {

		return articleRepository.getArticleId(id);
	}

	public List<Article> getArticles() {

		return articleRepository.getArticles();
	}

	public List<Article> getForArticles(int boardId, int itemsInAPage, int page, String searchKeyword,
			String searchType) {
		
		int limitFrom = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;

		return articleRepository.getForArticles(boardId, limitFrom, limitTake, searchKeyword, searchType);
	}

	public int getArticleCount(int boardId, String searchKeyword, String searchType) {
		
		return articleRepository.getArticleCount(boardId, searchKeyword, searchType);
	}

	public ResultData increaseHitCount(int id) {
		
		int affectedRow = articleRepository.increaseHitCount(id);

		if (affectedRow == 0) {
			return ResultData.from("F-1", "해당 게시글 없음", "id", id);
		}

		return ResultData.from("S-1", "조회수 증가", "id", id);
	}


	public Object getArticleHitCount(int id) {
		
		return articleRepository.getArticleHitCount(id);
	}

	public ResultData increaseLikeCount(int id) {
		
		int affectedCount = articleRepository.increaseLikeCount(id);
		
		if(affectedCount == 0) {
			
		}
		
		return ResultData.from("S-1", "좋아요 수 증가", "id", id);
	}

	public Object getArticleLikeCount(int id) {
		
		return articleRepository.getArticleLikeCount(id);
	}

}
