<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	
	<h1>드라마 추천 사이트</h1>
	<h3>서로서로 드라마를 추천해주세요</h3>
	
	<c:choose>
		<c:when test='${not empty id && isLogon==true}'>
			<h1>환영합니다. ${id}님</h1>
			<a href="/drama07/member07/logoutMember">로그아웃</a><br>
			<a href="/drama07/member07/modMemberForm">정보 수정 하기</a><br>			
		</c:when>
		<c:otherwise>
			<h1>환영합니다.</h1>
			<a href="/drama07/member07/loginForm">로그인 하기</a><br>
		</c:otherwise>
	</c:choose>
	
	<a href="/drama07/board07/listArticles">게시판 가기</a><br>
	
	
	
	
	
	
	
</body>
</html>