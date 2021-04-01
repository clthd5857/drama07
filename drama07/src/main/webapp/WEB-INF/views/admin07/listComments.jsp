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
					관리자 덧글 목록	
				</c:when>
				<c:otherwise>
					삭제 요청 덧글 목록
				</c:otherwise>
			</c:choose>
		</h1>
	</div>	
	
	<table class="table1">
		<tr class="tr1">
			<td>덧글 등록 번호</td>
			<td>게시판 번호</td>
			<td>덧글 내용</td>
			<td>등록일자(수정일자)</td>
			<td>ID</td>
			<td>삭제</td>
		</tr>	
		<c:choose>
			<c:when test="${not empty commentsList}">
				<c:forEach var="comment" items="${commentsList }">
					<tr>
						<td>${comment.commentArticleNO }</td>
						<td><a href="/drama07/board07/viewArticle?articleNO=${comment.articleNO}">${comment.articleNO}</a></td>
						<td>${comment.commentContent }</td>
						<td>
							<fmt:formatDate value='${comment.commentWriteDate}' pattern='yyyy/MM/dd HH:mm:ss'/><br>
							(<fmt:formatDate value='${comment.commentModDate}' pattern='yyyy/MM/dd HH:mm:ss'/>)
						</td>
						<td>${comment.guestId}(${comment.id})</td>
						<td>
							<c:if test="${nameflag==2}">
									<input type="button" value="복구" onClick="restoreComment('/drama07/board07/restoreComment',${comment.commentArticleNO})">
								</c:if>
							<input type="button" value="삭제" onClick="delComment('/drama07/board07/delComment',${comment.commentArticleNO},${nameflag})">
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan=4>
						<b>등록된 덧글이 없습니다.</b>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	
	<div>
		<form name="frm" action="/drama07/admin07/${name}">
			<table style="text-align:center; margin:auto;">
				<tr>
					<td>작성자</td>
				</tr>
				<tr>
					<td><input type="text" name="guestId"></td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="검색">
						<input type="button" value="초기화" onClick="resetArticleList('/drama07/admin07/${name}')">
					</td>
				</tr>
			</table>
		</form>
	</div>	
	<script>
	
	function delComment(url, commentArticleNO, nameflag){
		var result=confirm("게시글을 삭제하시겠습니까?");
		if(result){
			var sendform = document.createElement("form");
			sendform.setAttribute("method", "post");
			sendform.setAttribute("action", url);
			
			var commentArticleNOInput = document.createElement("input");
			commentArticleNOInput.setAttribute("type", "hidden");
			commentArticleNOInput.setAttribute("name", "commentArticleNO");
			commentArticleNOInput.setAttribute("value", commentArticleNO);
			
			var nameflagInput = document.createElement("input");
			nameflagInput.setAttribute("type", "hidden");
			nameflagInput.setAttribute("name", "nameflag");
			nameflagInput.setAttribute("value", nameflag);
			
			sendform.appendChild(commentArticleNOInput);
			sendform.appendChild(nameflagInput);
			document.body.appendChild(sendform);
			sendform.submit();
		}
	};
	
	function restoreComment(url, commentArticleNO){
		var result=confirm("덧글을 복구하시겠습니까?");
		if(result){
			var sendform = document.createElement("form");
			sendform.setAttribute("method", "post");
			sendform.setAttribute("action", url);
			
			var commentArticleNOInput = document.createElement("input");
			commentArticleNOInput.setAttribute("type", "hidden");
			commentArticleNOInput.setAttribute("name", "commentArticleNO");
			commentArticleNOInput.setAttribute("value", commentArticleNO);

			sendform.appendChild(commentArticleNOInput);
			document.body.appendChild(sendform);
			sendform.submit();
		}
	};
	
	
	
	function resetCommentList(url){
		var sendform = document.createElement("form");
		sendform.setAttribute("method", "get");
		sendform.setAttribute("action", url);
		
		document.body.appendChild(sendform);
		sendform.submit();
	};
	
	
	</script>
</body>
</html>