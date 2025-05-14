<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN"></c:set>
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="../member/doLogin" method="POST">

			<fieldset class="fieldset bg-base-200 border-base-300 rounded-box w-xs border p-4 ">
				<legend class="fieldset-legend">Login</legend>

				<label class="label" style="text-align: center;">아이디</label> <input name="loginId" autocomplete="off" type="text"
					placeholder="아이디 입력" /> <label class="label">비밀번호</label> <input name="loginPw" autocomplete="off" type="text"
					placeholder="비밀번호 입력" />

				<button class="btn btn-neutral mt-4">Login</button>
			</fieldset>


		</form>
		<div class="btns">
			<button type="button" onclick="history.back();">뒤로가기</button>

		</div>
	</div>
</section>



<%@ include file="../common/foot.jspf"%>