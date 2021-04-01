<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
		 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글쓰기창</title>
 	<style>
		.txtarea, #titleinput {width: 500px;}
	</style>
	<title>새글 쓰기 창</title>
</head>
<body>
	<fieldset style="width:1000px;">
		<legend><Strong><font size="30pt">새 글 쓰기</font></Strong></legend>
		<form name="articleForm" method="post" action="/drama07/board07/addArticle" enctype="multipart/form-data">
			<table class="table1">
				<tr>
					<td align="right" valign="top">글제목 : </td>
					<td colspan="2" align="left">&nbsp;<input id="titleinput" type="text" size="67"  maxlength="500" name="title" /></td>
				</tr>
				<tr>
					<td align="right" valign="top">글머리 : </td>
					<td colspan="2" align="left">
						<input type="radio" name="headName" value="추천" checked> 추천
						<input type="radio" name="headName" value="비추천"> 비추천
						<c:if test="${id=='hong'}">
							<input type="radio" name="headName" value="공지"> 공지
						</c:if>
					</td>
				</tr>
				<tr>
					<td align="right" valign="top">id : </td>
					<td colspan="2" align="left">&nbsp;<input id="idInput" type="text" size="67"  maxlength="500" name="id" value="${id}" readOnly/></td>
				</tr>
				<tr>
					<td align="right" valign="top"><br>글내용 : </td>
					<td colspan=2 align="left">&nbsp;
						<textarea id="txtarea" name="content" rows="10" cols="68" maxlength="4000" style="resize: none"
									placeholder="인터넷은 우리가 함께 만들어가는 소중한 공간입니다. 
									글 작성 시 타인에 대한 배려와 책임을 담아주세요."></textarea> </td>
				</tr>
				<tr>
					<td align="right" valign="top">이미지파일 첨부 :  </td>
					<td colspan=2>
						<div align="left">&nbsp;&nbsp;<input type="file" name="fileName" onChange="readURL(this);" multiple/></div><br>
						<img align="left" id="preview" src="<spring:url value='/resources/image/tv.jpg' />" alt="업로드 이미지가 없습니다." width="250px" height="250px"/>
					</td>
				</tr>
				<tr><td colspan=3><br></td></tr>
				<tr>
					<td colspan="3">&nbsp;
						<input class="btn" id="addArticle" type="button" value="글쓰기" width="100px" />
						<input class="btn" type="button" value="목록보기" width="100px" onClick="history.go(-1)" />
					</td>
				</tr>
			</table>
		</form>
	</fieldset>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function (e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	};
	
	$("#addArticle").on("click",function(){
		var title = $("#titleinput").val().trim();
		var txtarea = $("#txtarea").val().trim();

		if(title.length==0||title==""){
			alert("제목은 필수입니다.");
		} else if(txtarea.length==0||txtarea==""){
			alert("내용을 작성해주세요.");
		} else {
			articleForm.method="post";
			articleForm.action="/drama07/board07/addArticle";
			articleForm.submit();
		}

	});
</script>
</body>
</html>