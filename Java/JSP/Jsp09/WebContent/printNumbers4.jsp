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
	String max = request.getParameter("max");
	if(max != null){
		try{
			int maxValue = Integer.parseInt(max);
			
			for(int i = 0; i < maxValue; i++)
			{
				out.println(i +"<br>");
			}
		} catch(NumberFormatException e){
			out.println("<h1>숫자로 입력하세요!!</h1>");
		}
		
	} else {
		out.println("<h1>You must set 'max' parameter!!<h1>");
	}
%>
</body>
</html>