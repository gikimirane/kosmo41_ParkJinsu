<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
 	if(request.getAttribute("best") == null) {
 %>
	<jsp:forward page="first.do" />
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
<style>
   div nav a {
     font-family: 'Nanum Pen Script', cursive;
     font-size: 25px;
   }
   
   ul li{
   		display: inline;
   		margin: 60px;
   }
</style>
</head>
<body>
<div class="container">
      <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
          <div class="col-4 pt-1">
          	
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
        <div class="col-4 text-center">
        
        </div>
      </header>

      <div class="nav-scroller py-1 mb-2">
        <nav class="nav d-flex justify-content-between">
        <ul>
        	<li><a class="p-2 text-muted" href="notice.do">공지사항</a></li>
        	<li><a class="p-2 text-muted" href="chatt.do">대화방</a></li>
        	<li><a class="p-2 text-muted" href="list.do">글 게시판</a></li>
        	<li><a class="p-2 text-muted" href="picture.do">사진 게시판</a></li>
        	<li><a class="p-2 text-muted" href="modify.jsp">내 정보</a></li>
        </ul>
              
        </nav>
      </div>

      
        <div class="col-md-12 px-0">
        	<img src="호수.jpg" style="height:100%; width:100%; object-fit:contain">
        </div>
      

      <div class="row mb-2" >
        <div class="col-md-6" >
          <div class="card flex-md-row mb-4 shadow-sm h-md-250">
            <div class="card-body d-flex flex-column align-items-start" style="overflow:auto;">
              <strong class="d-inline-block mb-2 text-success">베스트 게시글</strong>      
               	 <c:choose>
               	 	<c:when test="${best == null}">
               	 		게시물이 없습니다.
               	 		<hr>
               	 	</c:when>
               	 	<c:otherwise>
               	 		<c:forEach items="${best}" var="best">
               	 			<a href="#"><h3>제목 : ${best.bTitle }</h3></a> <br>
               	 			아이디 : ${best.bName} &nbsp;&nbsp; 게시일 : ${best.bDate } &nbsp;&nbsp;<br> 조회수 : ${best.bHit }회 <br>
               	 			<%out.println("-----------------------------------------------------------------"); %>
               	 		</c:forEach>             	 		
               	 	</c:otherwise>
               	 </c:choose>
            </div>         
          </div>
        </div>
        <div class="col-md-6">
          <div class="card flex-md-row mb-4 shadow-sm h-md-250">
            <div class="card-body d-flex flex-column align-items-start">
              <strong class="d-inline-block mb-2 text-success">Design</strong>
              <h3 class="mb-0">
                <a class="text-dark" href="#">Post title</a>
              </h3>
              <div class="mb-1 text-muted">Nov 11</div>
              <p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
              <a href="#">Continue reading</a>
            </div>
            <img class="card-img-right flex-auto d-none d-lg-block" data-src="holder.js/200x250?theme=thumb" alt="Card image cap">
          </div>
        </div>
      </div>
 </div>

    <footer class="blog-footer">
      <p>Blog template built for <a href="https://getbootstrap.com/">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
      <p>
        <a href="#">Back to top</a>
      </p>
    </footer>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="https://getbootstrap.com/docs/4.1/assets/js/vendor/popper.min.js"></script>
    <script src="https://getbootstrap.com/docs/4.1/dist/js/bootstrap.min.js"></script>
    <script src="https://getbootstrap.com/docs/4.1/assets/js/vendor/holder.min.js"></script>
    <script>
      Holder.addTheme('thumb', {
        bg: '#55595c',
        fg: '#eceeef',
        text: 'Thumbnail'
      });
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>