<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입창</title>
</head>
<body>
	<fieldset>
	<legend style="font-size:22pt;">회원 가입</legend>
	<form name="frmMember">
		<table style="margin:auto;">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="id" id="f_id">
				</td>
				<td>
					<input type="button" id="idCheckBtn" value="아이디 중복검사">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" id="f_password"></td>
				<td></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="f_name"></td>
				<td></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email" id="f_email"></td>
				<td><input type="button" id="emailCheckBtn" value="이메일 중복검사"></td>
			</tr>
		</table>
	<p><input type="button" value="가입하기" id="joinMember">
	<input type="button" value="다시입력" onClick="history.go(0)"></p>
	<p><input type="button" value="로그인창 돌아가기" onClick="location.href='/drama07/member07/loginForm'"></p>
	
	</form>
	</fieldset>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$("#joinMember").on("click",function(){
			var id = $("#f_id").val().trim();
			var password = $("#f_password").val().trim();
			var name = $("#f_name").val().trim();
			var email = $("#f_email").val().trim();

			if(id.length==0||id==""){
				alert("아이디는 필수입니다.");
			} else if(password.length==0||password==""){
				alert("비밀번호는 필수입니다.");
			} else if(name.length==0||name==""){
				alert("이름은 필수입니다.");
			} else if(email.length==0||email==""){
				alert("이메일은 필수입니다.");
			} else if($('#idCheckBtn').prop("disabled")==false){
				alert("아이디 중복검사를 해주세요.")
			} else {
				frmMember.method="post";
				frmMember.action="/drama07/member07/joinMember";
				frmMember.submit();
			}

		});
 
 		$("#idCheckBtn").on("click",function(){
			var _id=$("#f_id").val();
			if($("#f_id").val().trim()==''){
				alert("아이디를 입력해주세요.");
				$("#f_id").focus();
				return;
			}
			

			$.ajax({
				type:"post",
				async:false,
				//url:"http://localhost:8080/drama07/member07/duplicateId",
				url:"/drama07/duplicateId",
				dataType:"text",
				data:{id:_id},
				success:function(data,textStatus){
					if(data=='usable'){
						$('#idCheckBtn').prop("disabled",true);
						$('#f_id').prop("readOnly",true);
						alert('사용할 수 있는 아이디입니다. 아이디를 바꾸시려면 초기화 해주세요.');
					} else {
						alert('사용할 수 없는 아이디입니다.');
					}
				},
				error:function(data,textStatus){
					alert('에러가 발생했습니다.');
				},
				complete:function(data,textStatus){
					
				}
			});
		});  

 		$("#emailCheckBtn").on("click",function(){
			var _email=$("#f_email").val();
			if($("#f_email").val().trim()==''){
				alert("아이디를 입력해주세요.");
				$("#f_email").focus();
				return;
			}
			

			$.ajax({
				type:"post",
				async:false,
				//url:"http://localhost:8080/drama07/member07/duplicateId",
				url:"/drama07/duplicateEmail",
				dataType:"text",
				data:{email:_email},
				success:function(data,textStatus){
					if(data=='usable'){
						$('#emailCheckBtn').prop("disabled",true);
						$('#f_email').prop("readOnly",true);
						alert('사용할 수 있는 이메일입니다. 이메일을 바꾸시려면 초기화 해주세요.');
					} else {
						alert('사용할 수 없는 이메일입니다.');
					}
				},
				error:function(data,textStatus){
					alert('에러가 발생했습니다.');
				},
				complete:function(data,textStatus){
					
				}
			});
		}); 
	</script>
	
</body>
</html>