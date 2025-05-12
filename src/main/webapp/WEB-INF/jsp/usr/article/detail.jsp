<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시글 상세보기"></c:set>
<%@ include file="../common/head.jspf"%>

<section class="mt-8 text-x1 px-4">
	<div class="mx-auto">
		<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<thead>
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
					<th style="text-align: center;">제목</th>
					<td style="text-align: center;">${article.title }</td>
				</tr>
				<tr>
					<th style="text-align: center;">작성자</th>
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

			
</section>

<%@ include file="../common/foot.jspf"%>
