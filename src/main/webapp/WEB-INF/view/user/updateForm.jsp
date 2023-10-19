<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<main>
	<div id="body">
	<div id="title">프로필 수정</div>
	<div class="update">
		<form action="/user/updateUser" method="post">
			<div id="info">
				<div>
					<input type="hidden" name="userId" value="${updateUserDto.userId}">
				</div>
				<div>
					<input type="text" placeholder="아이디" id="username" name="username"
						value="${updateUserDto.username}"
						<c:if test="${updateUserDto != null}">readonly</c:if>>
				</div>
				<div>
					<c:if test="${iskakaoUser == false}">
					<input type="password" placeholder="비밀번호*" id="password"
						name="password" value="">
					</c:if>
				</div>
				<div>
					<input type="text" placeholder="이름" id="name" name="name" value="${updateUserDto.name}">
				</div>
				<div>
					<input type="address" placeholder="주소" id="address" readonly value="${updateUserDto.address}"  name="address" class="narrow-input">
					<input type="button" onclick="openZipSearch()" value="검색" id="search" class="input-btn">
				</div>
				<div>
					<input type="text" placeholder="상세주소" id="address_detail" value="${updateUserDto.addressDetail}" name="addressDetail">
				</div>
				<div>
					<input type="email" placeholder="이메일" id="email" name="email" value="${updateUserDto.email}">
				</div>
				<div>
					<input type="tel" placeholder="연락처" id="phone_number"
						name="phoneNumber" value="${updateUserDto.phoneNumber}">
				</div>
				<div>
					<input type="date" palceholder="생년월일" id="birthday" name="birthday" value="${updateUserDto.birthday}">
				</div>
			</div>
			<br>
			<div class="submit-btn">
				<input type="submit" value="회원수정완료" onclick="update(this.form)">
			</div>
			<div class="sign-out">
				<input type="button" onclick="deleteUser()" value="회원탈퇴" id="delete" name="delete">
			</div>
			<br>
		</form>
	</div>
	</div>
	</main>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.10.2.min.js" /></script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function update(form) {
		console.log($("#address").val());
		form.submit;
	}
	function deleteUser() {
		window.location.href = '/user/delete';
	}

	function openZipSearch() {
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = '';
				if (data.userSelectedType === 'R') {
					addr = data.roadAddress;
				} else {
					addr = data.jibunAddress;
				}

				$("#address").val(addr);
				$("#address_detail").focus();
			}
		}).open();
	}
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>