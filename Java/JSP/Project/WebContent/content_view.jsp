<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
 	if(session.getAttribute("ValidMem") == null) {
 %>
	<jsp:forward page="login.jsp" />
<%
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<meta name="description" content="">
<meta name="author" content="">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- Bootstrap core CSS -->
<link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
<link href="https://getbootstrap.com/docs/4.1/examples/blog/blog.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script" rel="stylesheet">
<link href="form-validation.css" rel="stylesheet">

<script language="JavaScript" src="members.js"></script>

<style>
	div nav a{
     font-family: 'Nanum Pen Script', cursive;
     font-size: 30px;
   }
   
    div p{
     font-family: 'Nanum Pen Script', cursive;
     font-size: 30px;
   }
   form label{
	   font-family: 'Nanum Pen Script', cursive;
	     font-size: 30px;
   }
</style>
</head>
<body class="bg-light">
<div class="container">
	<header class="blog-header py-3">
		<div class="row flex-nowrap justify-content-between align-items-center">
        	<div class="col-4 pt-1">
          		<a href="write_view.do" class="badge badge-primary"><h5>글작성</h5></a>
          	</div>
        <div class="col-4 text-center">
            <a class="blog-header-logo text-dark" href="first.jsp">Project Web Site</a>
        </div>
        <div class="col-4 d-flex justify-content-end align-items-center">
          <%if(session.getAttribute("id") == null) {%>       
            <a class="btn btn-sm btn-outline-secondary" href="join.jsp">회원가입</a> &nbsp;
            <a class="btn btn-sm btn-outline-secondary" href="login.jsp">로그인</a>         
         <%} else { %>
         	<%=session.getAttribute("id") %>님 안녕하세요! &nbsp;&nbsp;
         	<a href = "logout.do">로그아웃</a>
         	<%} %>
        </div>
       </div>
       
      </header>
      
		<div class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-4 pt-1">
          		
          	</div>
       		<div class="col-4 text-center">
            	<a class="blog-header-logo text-dark" href="first.jsp">자유 게시판</a>
        	</div>
        	<div class="col-4 d-flex justify-content-end align-items-center">
        	</div>
       </div>
		<div class="nav-scroller py-1 mb-2">
       		<nav class="nav d-flex justify-content-between">
          		<a class="p-2 text-muted" href="notice.do">공지사항</a>
          		<a class="p-2 text-muted" href="chatt.do">대화방</a>
          		<a class="p-2 text-muted" href="list.do">자유 게시판</a>
          		<a class="p-2 text-muted" href="picture.do">사진 게시판</a>
          		<a class="p-2 text-muted" href="modify.jsp">내 정보</a>        
        	</nav>
      	</div>
	<table class="table table-striped">
  		<thead>
    		<tr>
      			<th scope="col" >제목</th>
      			<th scope="col" colspan="4">${content_view.bTitle}</th>

    		</tr>
  		</thead>
  		<tbody>
    		<tr>   			
    			<td colspan="2">아이디 : ${content_view.bName}</td>
    			<td>조회수 : ${content_view.bHit}회</td>
      			<th scope="row">번호 : ${content_view.bId}</th>
      			  			
    		</tr>
    		<tr>
    			<td>${content_view.bContent}</td> 
    		</tr>
    		
			
  		</tbody>
  		<tr>
	  		<%if(session.getAttribute("check") != null){ %>
	  			<td colspan="5"> 
					<a href="modify_view.do?bId=${content_view.bId}">수정</a> &nbsp;&nbsp;
					<a href="notice.do?page=<%=session.getAttribute("cpage")%>">목록보기</a> &nbsp;&nbsp;
					<a href="delete.do?bId=${content_view.bId}">삭제</a> &nbsp;&nbsp;
					<a href="reply_view.do?bId=${content_view.bId}">답변</a>
				</td>
			<%} else { %>
				<td colspan="5"> 
					<a href="notice.do?page=<%=session.getAttribute("cpage")%>">목록보기</a> &nbsp;&nbsp;	
					<a href="reply_view.do?bId=${content_view.bId}">답변</a>
				</td>
			<%} %>
		</tr>
</table>
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>