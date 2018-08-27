<%@page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if(request.isRequestedSessionIdValid()){
			String sName;
			String sValue;
			Enumeration enumeration = session.getAttributeNames();
			while(enumeration.hasMoreElements()){
				sName = enumeration.nextElement().toString();
				sValue = session.getAttribute(sName).toString();
				out.println(sName + " : " + sValue + "<br>");
			}
		}
		else {
			out.println("세션이 없습니다.");
		}
		
	%>
</body>
</html>