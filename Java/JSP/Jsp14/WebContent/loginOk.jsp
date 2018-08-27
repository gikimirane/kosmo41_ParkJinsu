<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP/Servlet 13-2</title>
</head>
<body>
	<%!
		String id, pw, name;
	%>
	<%
		request.setCharacterEncoding("UTF-8");
		
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		name = request.getParameter("name");
		
		if(id == null)
		{
			response.sendRedirect("login.html");
		}else{
			if(id.equals("abcde") && pw.equals("12345")) {
				session.setAttribute("id", id);			
				session.setAttribute("name", name);
				session.setMaxInactiveInterval(60); //쿠키와 다르게 서버에서 설정하는 것이기 때문에 한번만 설정하면 된다.
													//
				
				response.sendRedirect("welcome.jsp");
			}
			else {
				response.sendRedirect("login.html");
			}
		}
		
		
	%>
</body>
</html>