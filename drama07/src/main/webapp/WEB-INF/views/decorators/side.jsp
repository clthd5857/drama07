<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
	<fieldset>
	
	<h1>메뉴</h1>
	<hr>
	<c:choose>
		<c:when test="${id=='hong'}">
			<h1>
				<a href="/drama07/admin07/listMembers" class="no-underline">회원 목록</a>		
			</h1><hr>
			<h1>
				<a href="/drama07/admin07/flagMembers" class="no-underline">삭제 요청<br>회원 목록</a>
			</h1><hr>
			<h1>
				<a href="/drama07/admin07/listAdminArticles" class="no-underline">게시판 목록</a>		
			</h1><hr>
			<h1>
				<a href="/drama07/admin07/listFlagArticles" class="no-underline">삭제 요청<br>게시판 목록</a>
			</h1><hr>
			<h1>
				<a href="/drama07/admin07/listComments" class="no-underline">덧글 목록</a>		
			</h1><hr>
			<h1>
				<a href="/drama07/admin07/listFlagComments" class="no-underline">삭제 요청<br>덧글 목록</a>
			</h1><hr>
		</c:when>
		<c:otherwise>
			<h1>
			    <a href="/drama07/board07/listArticles" class="no-underline">추천 게시판</a>
		    </h1><hr>
			
			<c:if test='${not empty id && isLogon==true}'>
			    <h1>
			    	<a href="/drama07/member07/modMemberForm" class="no-underline">회원 정보 수정</a>
			    </h1><hr>
		    </c:if>
		</c:otherwise>
	</c:choose>
    
    </fieldset>