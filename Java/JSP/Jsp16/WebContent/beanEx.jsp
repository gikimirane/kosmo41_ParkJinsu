<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="student" class="com.study.jsp.Student" scope="page"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jsp Bean</title>
</head>
<body>
	<jsp:setProperty property="name" name="student" value="홍길동"/>
	<jsp:setProperty property="age" name="student" value="13"/>
	<jsp:setProperty property="grade" name="student" value="6"/>
	<jsp:setProperty property="studentNum" name="student" value="7"/>
	
	이름 : <jsp:getProperty property="name" name="student"/><br>
	나이 : <jsp:getProperty property="age" name="student"/><br>
	학년 : <jsp:getProperty property="grade" name="student"/><br>
	번호 : <jsp:getProperty property="studentNum" name="student"/><br>
</body>
</html>