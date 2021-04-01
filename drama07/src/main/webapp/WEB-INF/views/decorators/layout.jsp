<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<decorator:title default="layout" />
</title>
<style>
	#container {
	  width: 100%;
	  margin: 0px auto;
	  text-align:center;
	  border: 0px solid #bcbcbc;
	}
	#header {
	  padding: 5px;
	  margin-bottom: 5px;
	  border: 0px solid #bcbcbc;
	  /* background-color: lightgreen; */
	}
	#sidebar-left {
	  width: 15%;
	  height:700px;
	  padding: 5px;
	  margin-right: 5px;
	  margin-bottom: 5px;
	  float: left;
	  /* background-color: yellow; */
	  border: 0px solid #bcbcbc;
	  font-size:10px;
	}
	#content {
	  width: 75%;
	  padding: 5px;
	  margin-right: 5px;
	  float: left;
	  border: 0px solid #bcbcbc;
	}
	#footer {
	  clear: both;
	  padding: 5px;
	  border: 0px solid #bcbcbc;
	  /* background-color: lightblue; */
	}

    .no-underline {text-decoration:none;}
    .table1 {margin: auto;  text-align:center; font-size:13px; border: 1px lightgrey solid; width: 80%}
	.div1 {margin: auto;  width: 80%}
	.table1 tr td {padding:3px; border: 1px lightgrey solid; }
	.no-uline {text-decoration:none;}
	.sel-page {text-decoration:none;color:red;}
	.cls1 {margin: auto; text-align:center; text-decoration:none;}
	.cls2 {margin: auto; text-align:center; font-size:20px;}
    
    input[type="text" disabled]{border: 0px;}
   	input[type="button"] {cursor:pointer;}
    
</style>
<decorator:head/>
</head>
<body>
	<div id="container">
		<div id="header">
			<jsp:include page="header.jsp" flush="ture"></jsp:include>
		</div>
		
		<div id="sidebar-left">
			<jsp:include page="side.jsp" flush="ture"></jsp:include>
		</div>
		<div id="content">
			<decorator:body/>
		</div>
		
		<div id="footer">
			<jsp:include page="footer.jsp" flush="ture"></jsp:include>
		</div>
	</div>
</body>
</html>