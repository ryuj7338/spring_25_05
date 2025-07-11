<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="회원가입"></c:set>
<%@ include file="../common/head.jspf"%>

<!-- lodash debounce -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js"></script>
<script>
	let validLoginId = "";
	function JoinForm__submit(form) {

		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value == 0) {
			alert('아이디를 입력해주세요');
			return;
		}
		if (form.loginId.value != validLoginId) {
			alert('사용할 수 없는 아이디야');
			form.loginId.focus();
			return;
		}
		if (validLoginId == form.loginId.value) {
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value == 0) {
			alert('비밀번호를 입력해주세요');
			return;
		}
		form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		if (form.loginPwConfirm.value == 0) {
			alert('비밀번호 확인을 입력해주세요');
			return;
		}
		if (form.loginPwConfirm.value != form.loginPw.value) {
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.focus();
			return;
		}
		form.name.value = form.name.value.trim();
		if (form.name.value == 0) {
			alert('이름을 입력해주세요');
			return;
		}
		form.nickname.value = form.nickname.value.trim();
		if (form.nickname.value == 0) {
			alert('닉네임을 입력해주세요');
			return;
		}
		form.email.value = form.email.value.trim();
		if (form.email.value == 0) {
			alert('이메일을 입력해주세요');
			return;
		}
		form.cellphoneNum.value = form.cellphoneNum.value.trim();
		if (form.cellphoneNum.value == 0) {
			alert('전화번호를 입력해주세요');
			return;
		}
		submitJoinFormDone = true;
		form.submit();
	}

	function checkLoginIdDup(el) {
		$('.checkDup-msg').empty();
		const form = $(el).closest('form').get(0);
		if (form.loginId.value.length == 0) {
			validLoginId = '';
			return;
		}
		$.get('../member/getLoginIdDup', {
			isAjax : 'Y',
			loginId : form.loginId.value
		}, function(data) {
			$('.checkDup-msg').html('<div class="mt-2">' + data.msg + '</div>')
			if (data.success) {
				validLoginId = data.data1;
			} else {
				validLoginId = '';
			}
		}, 'json');
	}
	const checkLoginIdDupDebounced = _.debounce(checkLoginIdDup, 600);
</script>


<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="../member/doJoin" method="POST" onsubmit="JoinForm__submit(this); return false;">
			<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<tbody>
					<tr>
						<th>아이디</th>
						<td style="text-align: center;">
							<input onkeyup="checkLoginIdDupDebounced(this);"
								class="input input-bordered input-primary input-sm w-full max-w-xs" name="loginId" autocomplete="off"
								type="text" placeholder="아이디를 입력하세요" />
						</td>
					</tr>
					<tr>
						<th></th>
						<td style="text-align: center;">
							<div class="checkDup-msg"></div>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td style="text-align: center;"><input name="loginPw" autocomplete="off" type="text" placeholder="비밀번호 " />
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td style="text-align: center;"><input name="email" autocomplete="off" type="email" placeholder="이메일 " /></td>
					</tr>
					<tr>
						<th>이름</th>
						<td style="text-align: center;"><input name="name" autocomplete=" off" type="text" placeholder="이름 " /></td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td style="text-align: center;"><input name="nickname" autocomplete="off" type="text" placeholder="닉네임 " />
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td style="text-align: center;"><input name="cellphoneNum" autocomplete="off" type="text" placeholder="010-1234-1234 " />
						</td>
					</tr>
					<tr>
						<th></th>
						<td style="text-align: center;"><input value="회원가입" type="submit" /></td>
					</tr>

				</tbody>
			</table>
		</form>
		<div class="btns">
			<button type="button" onclick="history.back();">뒤로가기</button>

		</div>
	</div>
</section>



<%@ include file="../common/foot.jspf"%>