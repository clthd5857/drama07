<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%	request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글 보기</title>
</head>
<body>
	<form name="frmArticle" enctype="multipart/form-data">
		<table class="table1">
			<tr>
				<td width="10%" align="center" bgcolor="#FF9933">글번호</td>
				<td width="30%">
					${boardVO.articleNO}
					<input type="hidden" name="articleNO" value="${boardVO.articleNO}" />
				</td>
				<td width="10%" align="center" bgcolor="#FF9933">작성일</td>
				<td width="30%">
					<fmt:formatDate value='${boardVO.writeDate}' pattern='yyyy/MM/dd HH:mm:ss'/>
					<input type="hidden" name="writeDate" value="${boardVO.writeDate}" />
				</td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FF9933">작성자 아이디</td>
				<td>
					${boardVO.id}
					<input type="hidden" value="${boardVO.id}" name="id" />
				</td>
				<td align="center" bgcolor="#FF9933">수정일</td>
				<td>
					(<fmt:formatDate value='${boardVO.modDate}' pattern='yyyy/MM/dd HH:mm:ss'/>)
				</td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FF9933">글제목</td>
				<td colspan="3">
					<input type="text" value="${boardVO.title}" name="title" id="i_title" style="width:430px" disabled />
				</td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FF9933">글내용</td>
				<td colspan="3">
					<textarea rows="20" cols="60" name="content" id="i_content" disabled>${boardVO.content}</textarea>
				</td>  
			</tr>
			<tr>
				<td align="center" bgcolor="#FF9933">이미지</td>
				<c:choose>
					<c:when test="${not empty boardVO.fileName && boardVO.fileName.length() != 0}">
						<td colspan="3">
							<input type= "hidden" name="originalFileName" value="${boardVO.fileName}" />
						    <img src="/drama07/download?articleNO=${boardVO.articleNO}&fileName=${boardVO.fileName}" alt="${boardVO.fileName}" width="200px" height="200px" id="preview" /><br>
						    <input type="file" name="fileName" id="i_fileName" onchange="readURL(this);" style="display:none;"/>
						</td>
					</c:when>
					<c:otherwise>
						<td colspan="3">
							<img id="preview" src="" alt="이미지가 없습니다." width="200px" height="200px"  /><br>
							<input type="file" name="fileName" id="i_fileName" onchange="readURL(this);" style="display:none;"/>
						</td>
					</c:otherwise>
				</c:choose>
			</tr>
			
			<tr>
				<td colspan="4" >
					<span id="tr_btn">
						<c:if test="${boardVO.id==id || id=='hong'}">
							<input type=button value="수정" onClick="inputEnable(this.form)">
						</c:if>
					</span>
					<span id="tr_btn_modify" style="display:none;">
						<input type="button" value="반영" onClick="modifyArticle(frmArticle)"/>
						<input type="button" onClick="history.go(0)" value="취소">
						<input type="button" value="삭제" onClick="removeArticle('/drama07/board07/removeArticle',${boardVO.articleNO})">
					</span>
					<input type="button" value="글목록" onClick="backToList('/drama07/board07/listArticles')">					
				</td>
			</tr>
		</table>
	</form>
	<br><br>

	
	
	<table class="table1">
		<tr>
			<td width="5%">번호</td>
			<td width="45%">덧글 내용</td>
			<td width="15%">작성일자<br>(수정일자)</td>
			<td width="15%">작성자</td>
		</tr>	
			<c:choose>
				<c:when test="${not empty commentsList}">
					<c:forEach var="comment" items="${commentsList}" varStatus="commentNum">		
						<form name="frmModComment${commentNum.count}">
							<tr>
								<td>${commentNum.count}</td>
								<td><textarea cols="50" id="modComment${commentNum.count}" name="commentContent" disabled>${comment.commentContent}</textarea></td>
								<td>
									<font size="2">
										<fmt:formatDate value='${comment.commentWriteDate}' pattern="yy.MM.dd hh:mm"/><br>
										(<fmt:formatDate value='${comment.commentModDate}' pattern="yy.MM.dd hh:mm"/>)
									</font>
								</td>
								<td>
									${comment.guestId}<br>
									<input type="hidden" name="articleNO" value="${boardVO.articleNO}">
									<input type="hidden" name="commentArticleNO" value="${comment.commentArticleNO}">
									
									<c:if test="${(comment.guestId==id && comment.id==id) || id=='hong'}">
										<input type="button" id="com_btn${commentNum.count}" onClick="inputEnable2(this.form,${commentNum.count})" value="수정하기">
										<span id="com_btn_modify${commentNum.count}" style="display:none;">
											<input type="submit" onClick="modifyComment(this.form)" value="반영">
											<input type="button" onClick="history.go(0)" value="취소">
											<input type="button" onClick="removeComment(this.form)" value="삭제">
										</span>
									</c:if>
								</td>
							</tr>
						</form>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">댓글을 입력해주세요</td>
					<tr>
				</c:otherwise>
			</c:choose>
	</table>	
	
	<br>
	<form name="frmComment" action="/drama07/board07/addComment">
		<table class="table1">
			<tr>
				<td width="10%">id</td>
				<td width="60%" colspan="3">글내용</td>
				<td width="10%">입력하기</td>
			</tr>
			<tr>
				<td>
					<c:choose>
						<c:when test="${not empty id}">
							${id}
							<input type="hidden" name="guestId" value="${id}">
							<input type="hidden" name="id" value="${id}">
						</c:when>
						<c:otherwise>
							<input type="text" name="guestId" style="width:80px">
							<input type="hidden" name="id" value="guest">
						</c:otherwise>
					</c:choose>
				</td>	
				<td colspan="3"><textarea cols="60" name="commentContent" placeholder="인터넷은 우리가 함께 만들어가는 소중한 공간입니다.
댓글 작성 시 타인에 대한 배려와 책임을 담아주세요."></textarea></td>
				<td>
					<input type="hidden" name="articleNO" value="${boardVO.articleNO}">
					<input type="submit" value="입력">
				</td>	
			</tr>
		</table>
	</form>
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function (e) {
					$('#preview').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		};
		
		function backToList(url){
			var back=document.createElement("form");
			back.setAttribute("action",url);
			document.body.appendChild(back);
			back.submit();
		};
		
		function inputEnable(obj){
			document.getElementById("i_title").disabled=false;
			document.getElementById("i_content").disabled=false;
			document.getElementById("i_fileName").style.display="inline";
			//document.getElementById("tr_btn_modify").style.display="block";
			document.getElementById("tr_btn_modify").style.display="inline";
			document.getElementById("tr_btn").style.display="none";
		};
		
		function inputEnable2(obj,count){
			var modCommentId = "modComment"+count;
			//alert(modCommentId);
			var com_btnId = "com_btn"+count;
			var com_btn_modifyId = "com_btn_modify"+count;
			document.getElementById(modCommentId).disabled=false;
			document.getElementById(com_btnId).style.display="none";
			document.getElementById(com_btn_modifyId).style.display="inline";
			
		};
		
		
		function modifyArticle(obj){
			var result=confirm("게시글을 수정하시겠습니까?");
			if(result){
				obj.action="/drama07/board07/modArticle";
				obj.method="post"
				obj.submit();
			}
		};

		function removeArticle(url, articleNO){
			var result=confirm("게시글을 삭제하시겠습니까?");
			if(result){
				var sendform = document.createElement("form");
				sendform.setAttribute("method", "post");
				sendform.setAttribute("action", url);
				sendform.setAttribute("enctype", "application/www-form-urlencoded");
				
				var articleNOInput = document.createElement("input");
				articleNOInput.setAttribute("type", "hidden");
				articleNOInput.setAttribute("name", "articleNO");
				articleNOInput.setAttribute("value", articleNO);
				
				sendform.appendChild(articleNOInput);
				document.body.appendChild(sendform);
				sendform.submit();
			}
		};
		
		function modifyComment(obj){
			var result=confirm("덧글을 수정하시겠습니까?");
			if(result){
				obj.action="/drama07/board07/modComment";
				obj.method="post"
				obj.submit();
			}
		};
		
		function removeComment(obj){
			var result=confirm("덧글을 삭제하시겠습니까?");
			if(result){
				obj.action="/drama07/board07/removeComment";
				obj.method="post"
				obj.submit();
			}
		};
	
	</script>
</body>
</html>