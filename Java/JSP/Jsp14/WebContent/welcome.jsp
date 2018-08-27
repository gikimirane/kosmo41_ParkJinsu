<%@page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP/Servlet 13-2</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		Object obj1 = session.getAttribute("id");
		String id = (String)obj1;
		if(id==null){
			response.sendRedirect("login.html");
		}else{
			out.println(id + "님 안녕하세요." +"<br>");	
		}
		
	%>
	
	<a href="logout.jsp">로그아웃</a>
	
	<a href="sessiontest.jsp">세션 테스트</a>
</body>
</html>