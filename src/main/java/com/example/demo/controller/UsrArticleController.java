package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.interceptor.BeforeActionInterceptor;
import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {

	private final BeforeActionInterceptor beforeActionInterceptor;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private Rq rq;

	UsrArticleController(BeforeActionInterceptor beforeActionInterceptor) {
		this.beforeActionInterceptor = beforeActionInterceptor;
	}

	// 액션 메서드
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, String title, String body, String boardId) {

		Rq rq = (Rq) req.getAttribute("rq");

		if (Ut.isEmptyOrNull(title)) {
			return Ut.jsHistoryBack("F-1", "제목을 입력하세요.");
		}

		if (Ut.isEmptyOrNull(body)) {
			return Ut.jsHistoryBack("F-2", "내용을 입력하세요.");
		}

		if (Ut.isEmptyOrNull(boardId)) {
			return Ut.jsHistoryBack("F-3", "게시판을 선택하세요");
		}

		ResultData doWriteRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body, boardId);

		int id = (int) doWriteRd.getData1();

		Article article = articleService.getArticleId(id);

		return Ut.jsReplace(doWriteRd.getResultCode(), doWriteRd.getMsg(), "../article/list");
	}

	@RequestMapping("/usr/article/write")
	public String showWrite() {

		return "/usr/article/write";
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {

		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getArticleId(id);

		if (article == null) {

			return Ut.jsHistoryBack("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}

		ResultData userCanDeleteRd = articleService.userCanDelete(rq.getLoginedMemberId(), article);

		if (userCanDeleteRd.isFail()) {
			return Ut.jsHistoryBack(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg());
		}

		if (userCanDeleteRd.isSuccess()) {
			articleService.deleteArticle(id);
		}

		return Ut.jsReplace(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg(), "../article/list");
	}

//	로그인 체크 -> 유무 체크 -> 권한 체크
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {

		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getArticleId(id);

		if (article == null) {
			return Ut.jsReplace("F-1", Ut.f("%d번 게시글은 없습니다.", id), "../article/list");
		}

		ResultData userCanModifyRd = articleService.userCanModify(rq.getLoginedMemberId(), article);

		if (userCanModifyRd.isFail()) {
			return Ut.jsHistoryBack(userCanModifyRd.getResultCode(), userCanModifyRd.getMsg());
		}

		if (userCanModifyRd.isSuccess()) {
			articleService.modifyArticle(id, title, body);
		}

		article = articleService.getArticleId(id);

		return Ut.jsReplace(userCanModifyRd.getResultCode(), userCanModifyRd.getMsg(), "../article/detail?id=" + id);
	}

	@RequestMapping("/usr/article/modify")
	public String showModify(HttpServletRequest req, Model model, int id) {

		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%번 게시글은 없습니다.", id));
		}

		model.addAttribute("article", article);

		return "/usr/article/modify";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		model.addAttribute("article", article);

		return "/usr/article/detail";
	}
	
	@RequestMapping("/usr/article/doIncreaseHitCountRd")
	@ResponseBody
	public ResultData doIncreaseHitCount(int id) {

		ResultData increaseHitCountRd = articleService.increaseHitCount(id);

		if (increaseHitCountRd.isFail()) {
			return increaseHitCountRd;
		}

		return ResultData.newData(increaseHitCountRd, "hitCount", articleService.getArticleHitCount(id));
	}

	@RequestMapping("/usr/article/doIncreaseLikeCountRd")
	@ResponseBody
	public ResultData doIncreaseLikeCount(int id) {

		ResultData increaseLikeCountRd = articleService.increaseLikeCount(id);

		if (increaseLikeCountRd.isFail()) {
			return increaseLikeCountRd;
		}

		return ResultData.newData(increaseLikeCountRd, "hitCount", articleService.getArticleLikeCount(id));
	}
	
	
	
	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, Model model, @RequestParam(defaultValue = "0") int boardId,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "title") String searchType) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		Board board = boardService.getBoardById(boardId);

		if (board == null) {
//			return rq.historyBackOnView("존재하지 않는 게시판입니다.");
		}

		int articlesCount = articleService.getArticleCount(boardId, searchKeyword, searchType);
		
		int itemsInAPage = 10;
		
		int pagesCount = (int) Math.ceil(articlesCount / (double) itemsInAPage);
		
		
		List<Article> articles = articleService.getForArticles(boardId, itemsInAPage, page, searchKeyword, searchType);
		

		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("searchType", searchType);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("articles", articles);
		model.addAttribute("boardId", boardId);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		
		return "/usr/article/list";
	}

}
