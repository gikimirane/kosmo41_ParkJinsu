<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>계산기_jsp</title>
</head>
<body>
<%!
	String num1, num2, cal;
	int result = 0;
%>
	
<%
	String sType = request.getMethod();
	
	if(sType.equals("GET")){
		request.setCharacterEncoding("UTF-8");
		
		num1 = request.getParameter("num1");
		num2 = request.getParameter("num2");
		int sNum1 = Integer.parseInt(num1);
		int sNum2 = Integer.parseInt(num2);
		
		cal = request.getParameter("cal");
	
		if(cal.equals("+"))
		{
			result = sNum1 + sNum2;
		}
		else if(cal.equals("-"))
		{
			result = sNum1 - sNum2;
		}
		else if(cal.equals("x"))
		{
			result = sNum1 * sNum2;
		}
		else if(cal.equals("/"))
		{
			result = sNum1 / sNum2;
		}
%>
<h1>계산 결과 : <%=sNum1 + cal + sNum2 + " = " + result%></h1>
<%
	} else {
%>
<h1>허용된 요청방식이 아닙니다.</h1>
<%
	}
%>
</body>
</html>