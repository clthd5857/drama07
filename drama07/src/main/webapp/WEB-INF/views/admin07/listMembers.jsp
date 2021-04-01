<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록 페이지</title>
<style>
	.tr1 {
		text-align : center;
		background-color : #FFFF66;
	}
</style>
</head>
<body>
	
	<div class="div1">
		<h1>
			<c:choose>
				<c:when test="${nameflag==1}">
					회원 목록 정보	
				</c:when>
				<c:otherwise>
					삭제 요청 회원 목록
				</c:otherwise>
			</c:choose>
		</h1>
	</div>
		<form name="frm">
			<table style="text-align:center; margin:auto;">
				<tr>
					<td>ID 검색</td>
					<td>이름 검색</td>
					<td>이메일 검색</td>
				</tr>
				<tr>
					<td><input type="text" name="id"></td>
					<td><input type="text" name="name"></td>
					<td><input type="text" name="email"></td>			
				</tr>
				<tr>
					<td colspan="3">
						<input type="button" value="조회하기" onClick="changeToLowerCase('/drama07/admin07/${name}')">
						<input type="submit" value="초기화">
					</td>
				</tr>
			</table>
		</form>

	<br>
	
	
	<table class="table1">
		<tr class="tr1">
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일</td>
			<td>삭제</td>
		</tr>	
		<c:choose>
			<c:when test="${not empty membersList}">
				<c:forEach var="mem" items="${membersList }">
					<tr>
						<td>${mem.id }</td>
						<td>${mem.password }</td>
						<td>${mem.name }</td>
						<td>${mem.email }</td>
						<td>${mem.joinDate }</td>
						<td>
							<c:if test="${nameflag==2}">
								<input type="button" value="복구" onClick="restoreMember('/drama07/member07/restoreMember','${mem.id}')">
							</c:if>
							<%-- <a href="/drama07/member07/deleteMember?id=${mem.id}">삭제하기</a> --%>
							<input type="button" value="삭제" onClick="delMember('/drama07/member07/delMember','${mem.id}',${nameflag})">						
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan=6>
						<b>등록된 회원이 없습니다.</b>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	
	<script>
	
	function delMember(url, id, nameflag){
		var result=confirm("회원을 삭제하시겠습니까?");
		if(result){
			var sendform = document.createElement("form");
			sendform.setAttribute("method", "post");
			sendform.setAttribute("action", url);
			
			var idInput = document.createElement("input");
			idInput.setAttribute("type", "hidden");
			idInput.setAttribute("name", "id");
			idInput.setAttribute("value", id);
			
			var nameflagInput = document.createElement("input");
			nameflagInput.setAttribute("type", "hidden");
			nameflagInput.setAttribute("name", "nameflag");
			nameflagInput.setAttribute("value", nameflag);
			
			sendform.appendChild(idInput);
			sendform.appendChild(nameflagInput);
			document.body.appendChild(sendform);
			sendform.submit();
		}
	};
	
	function restoreMember(url, id){
		var result=confirm("회원을 복구하시겠습니까?");
		if(result){
			var sendform = document.createElement("form");
			sendform.setAttribute("method", "post");
			sendform.setAttribute("action", url);
			
			var idInput = document.createElement("input");
			idInput.setAttribute("type", "hidden");
			idInput.setAttribute("name", "id");
			idInput.setAttribute("value", id);

			sendform.appendChild(idInput);
			document.body.appendChild(sendform);
			sendform.submit();
		}
	};
	
	
	
	function changeToLowerCase(url){
		var inputId=frm.id.value;
		frm.id.value=inputId.toLowerCase();
		
		var inputName=frm.name.value;
		frm.name.value=inputName.toLowerCase();
		
		var inputEmail=frm.email.value;
		frm.email.value=inputEmail.toLowerCase();
		
		frm.method='get';
		frm.action=url;
		frm.submit();	
	};

	</script>
</body>
</html>