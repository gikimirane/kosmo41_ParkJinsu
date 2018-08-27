<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Declaration</title>
</head>
<body>
<%!
	int i = 10;
	String str = "ABCDE";
%>

<%
	out.println("i = " + i + "<br/>");
	out.println("str = " + str + "<br/>");
	out.println("sum = " + sum(1,5) + "<br/>");
%>

<%!
	public int sum(int a, int b) {
		return a+b;
	}
%>
</body>
</html>