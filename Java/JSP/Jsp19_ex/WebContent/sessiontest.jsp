<%@page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		
		boolean check = true;

		String name;
		String value;
			
		Enumeration enu = session.getAttributeNames();
			
		while(enu.hasMoreElements())
		{
			name = enu.nextElement().toString();
			value = (String)session.getAttribute(name).toString();
			out.println(name +" : " + value+"<br>");
			check = false;
		}
		
		if(check)
		{
			out.println("세션이 없습니다.");
		}

	%>
</body>
</html>