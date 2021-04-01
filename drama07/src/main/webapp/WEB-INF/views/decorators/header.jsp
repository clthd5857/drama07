<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%request.setCharacterEncoding("UTF-8");%> 
	<table style="width:100%; border:0;">
	  <tr>
	     <td width="15%">
			<a href="/drama07/member07/index">
				<img src="<spring:url value='/resources/image/drama.jpg'/>" alt="이미지가 없습니다." style="width:200px"/>
			</a>
	     </td>
	     
		<c:choose>	     
			<c:when test="${id=='hong'}">
				<td width="70%">
					<strong><font size=30><a href="/drama07/admin07/adminPage" class="no-underline">관리자 페이지</a></font></strong>
				</td>
			</c:when>
			<c:otherwise>
				<td width="70%">
					<strong><font size=30><a href="/drama07/member07/index" class="no-underline">드라마 추천 게시판</a></font></strong>
				</td>
		    </c:otherwise>
		</c:choose>
		
	     <td width="15%">
	       <c:choose>
	          <c:when test="${not empty id && isLogon == true}">
	            <h3>환영합니다<br>${id}님!<br>
	            <a href="/drama07/member07/logoutMember">로그아웃</a></h3>
	          </c:when>
	          <c:otherwise>
		      	<h3>
		      		<a href="/drama07/member07/loginForm" class="no-underline">로그인</a><br><br>
		      		<a href="/drama07/member07/memberForm" class="no-underline">회원 가입</a>
		      	</h3>
		      </c:otherwise>
		   </c:choose>     
	     </td>
	  </tr>
	</table>
	<hr>