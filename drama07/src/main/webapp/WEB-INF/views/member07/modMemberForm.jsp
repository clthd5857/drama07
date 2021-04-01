<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정창</title>
<style>
	.table1{margin: auto; border: 1px solid black;}
	.table1 tr td{border: 1px solid black;}
</style>


</head>
<body>
	<form>
		<h1 class="text_center">회원 정보</h1>
		<table class="table1" style="margin:auto;">
			<tr>
				<td width="200"><p align="right">아이디 : </p></td>
				<td width="400" align="left">
					&nbsp;&nbsp;<input type="text" value="${memberVO.id}" disabled>
					<input type="hidden" name="id" value="${memberVO.id}">
				</td>
			</tr>
			<tr>
				<td><p align="right">비밀번호 : </p></td>
				<td align="left">&nbsp;&nbsp;<input type ="password" name="password" value="${memberVO.password}"></td>
			</tr>
			<tr>
				<td><p align="right">이름 : </p></td>
				<td align="left">&nbsp;&nbsp;<input type ="text" name="name" value="${memberVO.name}" disabled></td>
			</tr>
			<tr>
				<td><p align="right">email : </p></td>
				<td align="left">&nbsp;&nbsp;<input type ="text" name="email" value="${memberVO.email}"></td>
			</tr>
			<tr>
				<td><p align="right">가입일 : </p></td>
				<td align="left">&nbsp;&nbsp;${memberVO.joinDate}</td>
			</tr>
		</table>
		<br><br>
		<input type="button" value="수정하기" onClick="modifyMember(this.form)">
		<input type="reset" value="초기화" >
		<input type="button" value="회원 탈퇴" onClick="removeMember(this.form)">
	</form>
	<script>
	
	function modifyMember(obj){
		var result=confirm("수정하시겠습니까?");
		if(result){
			obj.action="/drama07/member07/modMember";
			obj.method="post"
			obj.submit();
		}
	};
	
	function removeMember(obj){
		var result=confirm("회원 탈퇴 하시겠습니까?");
		if(result){
			obj.action="/drama07/member07/removeMember";
			obj.method="post"
			obj.submit();
		}
	};
	
	
	
	</script>
</body>
</html>