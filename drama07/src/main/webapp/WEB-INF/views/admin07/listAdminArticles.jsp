<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글목록창</title>
</head>
<body>
	<div class="div1">
		<h1>
			<c:choose>
				<c:when test="${nameflag==1}">
					관리자 게시판 목록	
				</c:when>
				<c:otherwise>
					삭제 요청 게시판 목록
				</c:otherwise>
			</c:choose>
		</h1>
	</div>
	<form>
		<table class="table1">
			<tr style="font-size:15px; background-color:lightgreen ;height: 10">
				<td>글번호</td>              
				<td>작성자</td>
				<td>글머리</td>              
				<td>제목</td>
				<td>작성일(수정일)</td>
				<td>삭제</td>
			</tr>
			<c:choose>

				<c:when test="${not empty articlesList}">
					<c:forEach var="article" items="${articlesList}" varStatus="articleNum">
						<tr align="center">
							<td width="10%">${article.articleNO}</td>
							<td width="10%">${article.id }</td>
							<td width="10%">${article.headName}</td>
							<td width="50%" align='left'>
								<a class="cls1" href="/drama07/board07/viewArticle?articleNO=${article.articleNO}">${article.title}</a>
							</td>
							<td width="15%">
								<fmt:formatDate value="${article.writeDate}" pattern="yy/MM/dd HH:mm" /><br>
								(<fmt:formatDate value="${article.modDate}" pattern="yy/MM/dd HH:mm" />)
								<%-- <fmt:formatDate value="${article.writeDate}" type="both"/> --%>
							</td>
							<td>
								<c:if test="${nameflag==2}">
									<input type="button" value="복구" onClick="restoreArticle('/drama07/board07/restoreArticle',${article.articleNO})">
								</c:if>
								<input type="button" value="삭제" onClick="delArticle('/drama07/board07/delArticle',${article.articleNO},${nameflag})">
								<%-- <a class="cls1" href="/drama07/board07/delArticle?articleNO=${article.articleNO}">삭제</a> --%>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:when test="${empty articlesList}" >
					<tr height="10">
						<td colspan="6">
							<p align="center">
								<strong><span style="font-size:9pt;">등록된 글이 없습니다.</span></strong>
							</p>
						</td>
					</tr>
				</c:when>
			</c:choose>
		</table>
	</form>
	<br>
	
	<div>
		<form name="frm" action="/drama07/admin07/${name}">
			<table style="text-align:center; margin:auto;">
				<tr>
					<td>작성자 검색</td>
					<td>글머리</td>
					<td>제목 검색</td>
				</tr>
				<tr>
					<td><input type="text" name="id"></td>
					<td>
						<!-- <input type="text" name="headName"> -->
						<select name="headName">
							<option value="추천">추천</option>
							<option value="비추천">비추천</option>
						</select>
					</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="submit" value="검색">
						<input type="button" value="초기화" onClick="resetArticleList('/drama07/admin07/${name}')">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<c:if test="${nameflag!=2}">
		<form name="articlefrm">
			<div class="div1" style="text-align:right; margin:auto;">
				<input type="button" onClick="articleForm('${id}')" value="글쓰기" 
						style="width:100px; height:50px; font-size:20pt">
			</div>
		</form>
	</c:if>
	<script>
		function articleForm(id){
			var articlefrm = document.articlefrm;
			
			if(id==null||id==""){
				alert('글 쓰기는 회원만 가능합니다.');
			} else{
				articlefrm.method="get";
				articlefrm.action="/drama07/board07/articleForm";
				articlefrm.submit();
			}
			
		};
	
		function delArticle(url, articleNO, nameflag){
			var result=confirm("게시글을 삭제하시겠습니까?");
			if(result){
				var sendform = document.createElement("form");
				sendform.setAttribute("method", "post");
				sendform.setAttribute("action", url);
				
				var articleNOInput = document.createElement("input");
				articleNOInput.setAttribute("type", "hidden");
				articleNOInput.setAttribute("name", "articleNO");
				articleNOInput.setAttribute("value", articleNO);
				
				var nameflagInput = document.createElement("input");
				nameflagInput.setAttribute("type", "hidden");
				nameflagInput.setAttribute("name", "nameflag");
				nameflagInput.setAttribute("value", nameflag);
				
				sendform.appendChild(articleNOInput);
				sendform.appendChild(nameflagInput);
				document.body.appendChild(sendform);
				sendform.submit();
			}
		};
		
		function restoreArticle(url, articleNO){
			var result=confirm("게시글을 복구하시겠습니까?");
			if(result){
				var sendform = document.createElement("form");
				sendform.setAttribute("method", "post");
				sendform.setAttribute("action", url);
				
				var articleNOInput = document.createElement("input");
				articleNOInput.setAttribute("type", "hidden");
				articleNOInput.setAttribute("name", "articleNO");
				articleNOInput.setAttribute("value", articleNO);

				sendform.appendChild(articleNOInput);
				document.body.appendChild(sendform);
				sendform.submit();
			}
		};
		
		
		function resetArticleList(url){
			var sendform = document.createElement("form");
			sendform.setAttribute("method", "get");
			sendform.setAttribute("action", url);
			
			document.body.appendChild(sendform);
			sendform.submit();
		};
	</script>
</body>
</html>