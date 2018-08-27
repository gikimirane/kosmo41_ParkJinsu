<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수정</title>
</head>
<body>
	<%
		String id;
		id = (String)session.getAttribute("id");
		
		if(id == null)
		{
			response.sendRedirect("join.jsp");
		}
	%>

	<%=session.getAttribute("name") %>님의 회원정보 수정이 정상 처리 되었습니다.<br><p>
	
	<a href="logout.jsp">로그아웃</a> <a href="modify.jsp">정보수정</a>  &nbsp;<a href = "sessiontest.jsp">세션 정보 보기</a>
</body>
</html>