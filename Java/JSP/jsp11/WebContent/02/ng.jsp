<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP/Servlet 11-2</title>
</head>
<body>

<%!
	int age;
%>

<%
	String str = request.getParameter("age");
	age = Integer.parseInt(str);
%>
<h1>당신의 나이는 <%=age %>세이므로 <br>미성년자 입니다. 주류구매가 불가능합니다.</h1>

<a href="requestEx.html">처음으로 이동</a>
</body>
</html>