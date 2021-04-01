<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

<form name="frm">
	<fieldset>
		<legend style="font-size:22pt; text-align:center;"><strong>회원 로그인</strong></legend>
		<br>
		<table class="table1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
		</table>
		<p><input type="button" value="로그인" onClick="login()">
		<input type="reset" value="다시입력"></p><br><br>
		<!-- <p><input type="button" value="회원가입" onClick="location.href='MemberForm.html'"></p> -->
		<a href="/drama07/member07/memberForm">회원가입</a>
		<input type="hidden" value="first" name="loginP">
	</fieldset>
	
	<c:if test="${isLogon==false}">
		<script>
			alert('아이디 혹은 비밀번호가 틀렸습니다.');
		</script>
	</c:if>
	
</form>
	<script>
		function login(){
			var frm = document.frm;
			var id = frm.id.value;
			var password = frm.password.value;
			
			if(id==null||id==""){
				alert('아이디를 입력하세요.');
			} else if(password==null || password==""){
				alert('비밀번호를 입력하세요.');
			} else {
				frm.method="post";
				frm.action="/drama07/member07/loginMember";
				frm.submit();
			}
			
		};
	</script>
</body>
</html>