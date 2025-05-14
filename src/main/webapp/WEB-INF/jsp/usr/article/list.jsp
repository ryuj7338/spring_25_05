<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<link rel="stylesheet" href="/resource/common.css" />
<script src="/resource/common.js" defer="defer"></script>
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<!-- 폰트어썸 FREE 아이콘 리스트 : https://fontawesome.com/v5.15/icons?d=gallery&p=2&m=free -->

<!-- 테일윈드 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.1.4/tailwind.min.css">
<!-- 테일윈드 치트시트 : https://nerdcave.com/tailwind-cheat-sheet -->
</head>

<c:set var="pageTitle" value="${board.code }"></c:set>

<%@ include file="../common/head.jspf"%>



<section class="mt-24 text-xl px-4">
	<div class="mx-auto">
		<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">

			<thead>
				<tr>
					<th style="text-align: center;">ID</th>
					<th style="text-align: center;">제목</th>
					<th style="text-align: center;">작성자</th>
					<th style="text-align: center;">날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articles }">
					<tr>
						<td style="text-align: center;">${article.id}</td>
						<td style="text-align: center;"><a class="hover:underline" href="detail?id=${article.id }">${article.title }</a>
						</td>
						<td style="text-align: center;">${article.extra__writer }</td>
						<td style="text-align: center;">${article.regDate.substring(0,10)}</td>
					</tr>
				</c:forEach>

				<c:if test="${empty articles }">
					<tr>
						<td colspan="4" style="text-align: center;">게시글이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</section>

<select class="select select-neutral">
  <option disabled selected>선택</option>
  <option>제목</option>
  <option>내용</option>
  <option>작성자</option>
</select>

<div class="join mt-24">
	<button class="join-horizontal btn">1</button>
	<button class="join-horizontal btn">2</button>
	<button class="join-horizontal btn btn-disabled">...</button>
	<button class="join-horizontal btn">99</button>
	<button class="join-horizontal btn">100</button>
	<ul>
		<c:if test="${rq.isLogined() }">
			<li><a class="hover:underline" href="../article/write">글쓰기</a></li>
		</c:if>
	</ul>
</div>



<%@ include file="../common/foot.jspf"%>