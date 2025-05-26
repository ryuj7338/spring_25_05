<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<c:set var="pageTitle" value="게시글 상세보기"></c:set>
<%@ include file="../common/head.jspf"%>

<!-- 변수 -->
<script>
	const params = {};
	params.id = parseInt('${param.id }');
	
	var isAlreadyAddLikeRp = ${isAlreadyAddLikeRp};
	var isAlreadyAddDislikeRp = ${isAlreadyAddDislikeRp};
</script>

<!-- 좋아요 / 싫어요 -->
<script>
	function checkRP() {
		if (isAlreadyAddLikeRp == true) {
			$('#likeButton').toggleClass('btn-outline');
		} else if (isAlreadyAddDislikeRp == true) {
			$('#DislikeButton').toggleClass('btn-outline');
		} else {
				return;
		}
	}
	
	function doLikeReaction(articleId) {
		
		$.ajax({
			url : '/usr/reaction/doLike',
			type : 'POST',
			data : {
				relTypeCode : 'article',
				relId : articleId
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				console.log('data.data1Name : ' + data.data1Name);
				console.log('data.data1 : ' + data.data1);
				console.log('data.data2Name : ' + data.data2Name);
				console.log('data.data2 : ' + data.data2);
				if (data.resultCode.startsWith('S-')) {
					var likeButton = $('#likeButton');
					var likeCount = $('#likeCount');
					var likeCountC = $('.likeCount');
					var DislikeButton = $('#DislikeButton');
					var DislikeCount = $('#DislikeCount');
					var DislikeCountC = $('.DislikeCount');

					if (data.resultCode == 'S-1') {
						likeButton.toggleClass('btn-outline');
						likeCount.text(data.data1);
						likeCountC.text(data.data1);
					} else if (data.resultCode == 'S-2') {
						DislikeButton.toggleClass('btn-outline');
						DislikeCount.text(data.data2);
						DislikeCountC.text(data.data2);
						likeButton.toggleClass('btn-outline');
						likeCount.text(data.data1);
						likeCountC.text(data.data1);
					} else {
						likeButton.toggleClass('btn-outline');
						likeCount.text(data.data1);
						likeCountC.text(data.data1);
					}

				} else {
					alert(data.msg);
				}

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('좋아요 오류 발생 : ' + textStatus);

			}

		});
	}
	
	function doDislikeReaction(articleId) {

		$.ajax({
			url : '/usr/reaction/doDislike',
			type : 'POST',
			data : {
				relTypeCode : 'article',
				relId : articleId
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				console.log('data.data1Name : ' + data.data1Name);
				console.log('data.data1 : ' + data.data1);
				console.log('data.data2Name : ' + data.data2Name);
				console.log('data.data2 : ' + data.data2);
				if (data.resultCode.startsWith('S-')) {
					var likeButton = $('#likeButton');
					var likeCount = $('#likeCount');
					var likeCountC = $('.likeCount');
					var DislikeButton = $('#DislikeButton');
					var DislikeCount = $('#DislikeCount');
					var DislikeCountC = $('.DislikeCount');

					if (data.resultCode == 'S-1') {
						DislikeButton.toggleClass('btn-outline');
						DislikeCount.text(data.data2);
						DislikeCountC.text(data.data2);
					} else if (data.resultCode == 'S-2') {
						likeButton.toggleClass('btn-outline');
						likeCount.text(data.data1);
						likeCountC.text(data.data1);
						DislikeButton.toggleClass('btn-outline');
						DislikeCount.text(data.data2);
						DislikeCountC.text(data.data2);

					} else {
						DislikeButton.toggleClass('btn-outline');
						DislikeCount.text(data.data2);
						DislikeCountC.text(data.data2);
					}

				} else {
					alert(data.msg);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('싫어요 오류 발생 : ' + textStatus);
			}

		});
	}

	$(function() {
		checkRP();
	});
</script>

<script>
	function ArticleDetail__doIncreaseHitCount() {
		
		const localStorageKey = 'article__' + params.id + '__alreadyOnView';
		
		if(localStorage.getItem(localStorageKey)) {
			return;
		}
		
		localStorage.setItem(localStorageKey, true);
		
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
					<th style="text-align: center;">좋아요 / 싫어요</th>
					<td style="text-align: center;">
						<button id="likeButton" class="btn btn-outline btn-success" onclick="doLikeReaction(${param.id})">
							LIKE👍 <span class="likeCount">${article.like}</span>
						</button>
						<button id="DislikeButton" class="btn btn-outline btn-error" onclick="doDislikeReaction(${param.id})">
							DISLIKE👎 <span class="DislikeCount">${article.dislike}</span>
						</button>
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">댓글수</th>
					<td style="text-align: center;"><span class="article-detail__reply-count">${article.replyCount }</span></td>
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


	</div class="btns">
	<button type="button" onclick="history.back();">뒤로가기</button>

	<c:if test="${article.userCanModify }">
		<a href="../article/modify?id=${article.id }">수정</a>
	</c:if>
	<c:if test="${article.userCanDelete }">
		<a href="../article/doDelete?id=${article.id }">삭제</a>
	</c:if>


<script>
	function ReplyWrite__submit(form) {
		console.log(form.body.value);
		
		form.body.value = form.body.value.trim();
		
		if(form.body.value.length < 3){
			alert('3글자 이상 입력하세요');
			form.body.focus();
			return;
		}
		
		form.submit();
	}
</script>

<!-- 댓글 -->
<section class="mt-24 text-xl px-4">
	<c:if test="${rq.isLogined() }">
		<form action="../reply/doWrite" method="POST" onsubmit="ReplyWrite__submit(this); return false;" )>
			<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<input type="hidden" name="relTypeCode" value="article" />
				<input type="hidden" name="relId" value="${article.id }" />
				<tbody>

					<tr>
						<th>댓글 내용 입력</th>
						<td style="text-align: center;">
							<textarea class="input input-bordered input-sm w-full max-w-xs" name="body" autocomplete="off" type="text"
								placeholder="내용을 입력하세요"></textarea>
						</td>

					</tr>
					<tr>
						<th></th>
						<td style="text-align: center;">
							<button class="btn btn-outline">작성</button>
						</td>

					</tr>
				</tbody>
			</table>
		</form>
	</c:if>

	<c:if test="${!rq.isLogined() }">
		댓글 작성을 위해 <a class="btn btn-outline btn-primary" href="../member/login">로그인</a>이 필요합니다
	</c:if>
	<!-- 	댓글 리스트 -->
	<div class="mx-auto">
		<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<thead>
				<tr>
					<th style="text-align: center;">Registration Date</th>
					<th style="text-align: center;">Writer</th>
					<th style="text-align: center;">Body</th>
					<th style="text-align: center;">Like</th>
					<th style="text-align: center;">Dislike</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reply" items="${replies}">
					<tr class="hover">
						<td style="text-align: center;">${reply.regDate.substring(0,10)}</td>
						<td style="text-align: center;">${reply.extra__writer}</td>
						<td style="text-align: center;">${reply.body}</td>
						<td style="text-align: center;">${reply.goodReactionPoint}</td>
						<td style="text-align: center;">${reply.badReactionPoint}</td>
					</tr>
				</c:forEach>

				<c:if test="${empty replies}">
					<tr>
						<td colspan="4" style="text-align: center;">댓글이 없습니다</td>
					</tr>
				</c:if>
			</tbody>
		</table>

	</div>
</section>


<%@ include file="../common/foot.jspf"%>
