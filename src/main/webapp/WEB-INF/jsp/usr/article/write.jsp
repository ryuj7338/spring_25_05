<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Modify"></c:set>
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="../article/dowrite" method="POST">
			<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<tbody>
					<tr>
						<th>제목</th>
						<td style="text-align: center;"><input type="text" placeholder="제목" style="width: 100%;" /></td>
					</tr>

					<tr>
						<th>내용</th>
						<td style="text-align: center;"><textarea type="text" placeholder="내용" cols="20" rows="10"
								style="width: 100%; height: 200px; resize: none;"></textarea></td>
					</tr>


				</tbody>
			</table>
			<button type="submit" onclick="">등록</button>
		</form>
		<div class="btns">
			<button type="button" onclick="history.back();">뒤로가기</button>
			
		</div>

	</div>
</section>



<%@ include file="../common/foot.jspf"%>