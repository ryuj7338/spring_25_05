<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
	<h1>게시글 목록</h1>

	<hr />

	<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
		<thead>
			<tr>
				<th style="text-align: center;">번호</th>
				<th style="text-align: center;">날짜</th>
				<th style="text-align: center;">제목</th>
				<th style="text-align: center;">작성자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles }">
				<tr>
					<td style="text-align: center;">${article.id}</td>
					<td style="text-align: center;">${article.regDate.substring(0,10)}</td>
					<td style="text-align: center;">
						<a href="detail?id=${article.id }" style="color:inherit; text-decoration: none;">${article.title }</a>
					</td>
					<td style="text-align: center;">${article.memberId }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



</body>
</html>