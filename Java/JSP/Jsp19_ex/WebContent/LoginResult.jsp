<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%!
    	String name, id, pw;
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
	<%
		name = (String)session.getAttribute("name");
		id = (String)session.getAttribute("id");
		pw = (String)session.getAttribute("pw");
		
		if(id == null)
		{
			response.sendRedirect("join.jsp");
		}
	%>
	
	<%=name %>님 안녕하세요 <br><p>
	
	<a href = "modify.jsp">회원정보 수정</a> &nbsp;<a href = "sessiontest.jsp">세션 정보 보기</a> &nbsp;<a href = "logout.jsp">로그아웃</a>
</body>
</html>