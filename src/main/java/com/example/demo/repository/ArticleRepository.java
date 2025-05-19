package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Mapper
public interface ArticleRepository {

//	@Insert("INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = #{title}, `body` = #{body}")
	public int writeArticle(int memberId, String title, String body, String boardId);

//	@Delete("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(int id);

//	@Update("UPDATE article SET updateDate = NOW(), title = #{title}, `body` = #{body} WHERE id = #{id}")
	public void modifyArticle(int id, String title, String body);

//	@Select("SELECT * FROM article WHERE id = #{id}")
	public Article getArticleId(int id);

//	@Select("SELECT * FROM article ORDER BY id DESC")
	public List<Article> getArticles();

	public int getLastInsertId();

	public Article getForPrintArticle(int loginedMemberId);

	public List<Article> getForPrintArticles(int boardId, int limitFrom, int limitTake);

	public int getArticleCount(int boardId, String searchKeyword, String searchType);

	public List<Article> getForArticles(int boardId, int limitFrom, int limitTake, String searchKeyword,
			String searchType);

//	조회수
	public int increaseHitCount(int id);

	public int getArticleHitCount(int id);

// 좋아요
	public int increaseLikeReaction(int relId);
	
}