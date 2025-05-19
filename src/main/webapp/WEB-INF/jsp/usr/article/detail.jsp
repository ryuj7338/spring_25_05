<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<c:set var="pageTitle" value="게시글 상세보기"></c:set>
<%@ include file="../common/head.jspf"%>


<script>
	const params = {};
	params.id = parseInt('${param.id }');
</script>

<script>
	function ArticleDetail__doIncreaseHitCount() {
		$.get('../article/doIncreaseHitCountRd', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data) {
			console.log(data);
			console.log(data.data1);
			console.log(data.msg);
			$('.article-detail__hit-count').html(data.data1);
		}, 'json');
	}

	$(function() {
		ArticleDetail__doIncreaseHitCount();
	})
</script>

<script>
	
	function likeCount() {
		if('.like-btn').click(function(){
			let likeOn = $('.like-btn');
			
			if (likeOn !)
		})
	}
</script>

<section class="mt-8 text-x1 px-4">
	<div class="mx-auto">
		<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<thead>
				<tr>
					<th style="text-align: center;">게시판 번호</th>
					<td style="text-align: center;">${article.boardId }</td>
				</tr>
				<tr>
					<th style="text-align: center;">번호</th>
					<td style="text-align: center;">${article.id}</td>
				</tr>
				<tr>
					<th style="text-align: center;">날짜</th>
					<td style="text-align: center;">${article.regDate}</td>
				</tr>
				<tr>
					<th style="text-align: center;">수정 날짜</th>
					<td style="text-align: center;">${article.updateDate}</td>
				</tr>

				<tr>
					<th style="text-align: center;">작성자</th>
					<td style="text-align: center;">${article.extra__writer}</td>
				</tr>
				<tr>
					<th style="text-align: center;">조회수</th>
					<td style="text-align: center;"><span class="article-detail__hit-count">${article.hit }</span></td>
				</tr>
				<tr>
					<th style="text-align: center;">좋아요 / 싫어요 ${usersReaction }</th>
					<td style="text-align: center;"><a
							href="/usr/reaction/doLike?relTypeCode=article&relId=${param.id }&replaceUri=${rq.currentUri}"
							class="btn btn-outline btn-success">LIKE👍 ${article.like }</a> <a
							href="/usr/reaction/doDislike?relTypeCode=article&relId=${param.id }&replaceUri=${rq.currentUri}"
							class="btn btn-outline btn-error">DISLIKE👎 ${article.dislike }</a>
						<button></button></td>
				</tr>
				<tr>
					<th style="text-align: center;">제목</th>
					<td style="text-align: center;">${article.title }</td>
				</tr>
				<tr>
					<th style="text-align: center;">내용</th>
					<td style="text-align: center;">${article.body }</td>
				</tr>
			</thead>
		</table>

		<div class="text-center mt-6">
			<form action="../article/detail" method="POST">
				<div class="inline-flex justify-center  items-center mx-auto border border-solid border-blue-400 p-3 rounded-lg">
					<div class="px-2">${article.extra__writer }</div>
					<input type="text" placeholder="댓글 입력" class="px-6" name="comment" />
					<button type="submit" class="px-2">등록</button>
				</div>
		</div>
		</form>
	</div>

	</div class="btns">
	<button type="button" onclick="history.back();">뒤로가기</button>

	<c:if test="${article.userCanModify }">
		<a href="../article/modify?id=${article.id }">수정</a>
	</c:if>
	<c:if test="${article.userCanDelete }">
		<a href="../article/doDelete?id=${article.id }">삭제</a>
	</c:if>


</section>

<%@ include file="../common/foot.jspf"%>
