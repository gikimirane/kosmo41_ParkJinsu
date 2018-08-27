<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	div {
		border:1px solid #cccccc;
		padding:5px; margin:5px;
		text-align:center;
	}
		
	#con {
		width:800px;
		margin:0 auto;
		ouerflow:hidden;
	}
		
	#header {
		width:780px; height:100px;
		line-height:100px;
	}
		
	#nav {
		width:780px; height:100px;
		overflow:hidden;
	}
	
	#nav ul li {
		width:138px; height:40px;
		line-height:40px;
		text-align:center;
		list-style: none;
		float:left;
		border:1px solid #dddddd;
	}
		
	#wrap {
		width:780;
		overflow: hidden;
	}
		
	#content {
		width:600px; height: 300px;
		float:left;
	}
		
	#login {
		width:120px; height:150px;
		float:left;
	}
		
	#banner {
		width:120px; height:150px;
		float:left;
		text-align:center;
	}
		
	#footer {
		width:780px; height:100px;
		line-height: 100px;
	}
</style>
</head>
<body>
<div id="con">
		<div id="header">
			HEADER
		</div>
		
		<div id="nav">
			<p>NAVIGATION</p>
			<ul>
				<li>menu1</li>
				<li>menu2</li>
				<li>menu3</li>
				<li>menu4</li>
				<li>menu5</li>
			</ul>
		</div>
		
		<div id="wrap">
			<div id="content">CONTENT</div>
			<div id="login" >
				
				<a href="login.jsp">로그인하세요</a><br>
				<a href="join.jsp">가입하세요</a>
			</div>
			<div id="banner">BANNER</div>
		</div>
		
		<div id="footer">
			FOOTER
		</div>
	</div>
</body>
</html>