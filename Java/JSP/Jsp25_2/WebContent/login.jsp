<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("ValidMem") != null) { %>
	<jsp:forward page="main.jsp"></jsp:forward>
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP/Servlet 21-1</title>
<style>
	form {
		
		text-align:center;
		margin:300px;
	}
	#id {
		
	}
</style>
</head>
<body>
	<form action="loginOk.do" method="post">
				
		<input type="text" name="id" placeholder="아이디" maxlength="30" style="width:300px; height:30px; font-size:20px;"><br>
		<br>
		<input type="password" name="pw" placeholder="비밀번호" style="width:300px; height:30px; font-size:20px;"><br><p>
		<input type="submit" value="로그인"> &nbsp;&nbsp;
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">
 	</form>
</body>
</html>