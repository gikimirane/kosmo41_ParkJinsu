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
<%session.setAttribute("bUrl", "first.do"); %>
<%String login = (String)session.getAttribute("login"); %>

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
   
   img{
   		max-width: 100%;
		width: 600px;
		max-height: 100%;
		height:600px;
   }
</style>

<meta name="google-signin-client_id" content="356131626574-6u6njs718ofrd2ju2bjtcqkmos8299rk.apps.googleusercontent.com">
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
          <%if(session.getAttribute("login") == null) {%>
          	      
            <a class="btn btn-sm btn-outline-secondary" href="join.jsp">회원가입</a> &nbsp;
            <a class="btn btn-sm btn-outline-secondary" href="login.jsp">로그인</a>       
         <%} else { %>
         	<%=session.getAttribute("id") %>님 안녕하세요! &nbsp;&nbsp;
         	
         	<%if(session.getAttribute("login").equals("google")){ %>
         	<script>
				function signOut() {
				    var auth2 = gapi.auth2.getAuthInstance();
				    auth2.signOut().then(function () {
				    	console.log('User signed out.');
				    	document.location.href="logout.do"
				    });
				}
				function onLoad() {
					gapi.load('auth2', function(){
						gapi.auth2.init();
					});
				}
			</script>
         	 <a href="#" onclick = "signOut();"class="badge badge-primary">로그아웃</a>
         	 <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
         	 
      		 <%} else if(session.getAttribute("login").equals("origin")) { %>
         	<a href = "logout.do" class="badge badge-primary">로그아웃</a>
         	<%} %>
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
        	<li><a class="p-2 text-muted" href="list.do">자유 게시판</a></li>
        	<li><a class="p-2 text-muted" href="picture.do">사진 게시판</a></li>
        	<li><a class="p-2 text-muted" href="modify.jsp">내 정보</a></li>
        </ul>
              
        </nav>
      </div>

      
        <div class="col-md-12 px-0">
        	<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
  				<div class="carousel-inner">
    				<div class="carousel-item active">
      					<img class="d-block w-100" src="./pictures/산.jpg" alt="첫번째 슬라이드">
    				</div>
    				<div class="carousel-item">
      					<img class="d-block w-100" src="./pictures/위인.jpg" alt="두번째 슬라이드">
    				</div>
    				<div class="carousel-item">
      					<img class="d-block w-100" src="./pictures/호수.jpg" alt="세번째 슬라이드">
    				</div>
  				</div>
  					<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
    					<span class="sr-only">이전</span>
 	 				</a>
  					<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    					<span class="carousel-control-next-icon" aria-hidden="true"></span>
    					<span class="sr-only">다음</span>
  					</a>
			</div>
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
               	 					<a href="content_view.do?bId=${best.bId}" onclick="change()"><h3>제목 : ${best.bTitle }</h3></a> <br>
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
            <div class="card-body d-flex flex-column align-items-start" style="overflow:auto;">
              <strong class="d-inline-block mb-2 text-success">공지사항</strong>
              <c:choose>
               	 <c:when test="${notice == null}">
               	 	게시물이 없습니다.
               	 	<hr>
               	 </c:when>
               	 <c:otherwise>
               	 	<c:forEach items="${notice}" var="notice">
               	 		<a href="content_view.do?bId=${notice.bId}" onclick="change2()"><h3>제목 : ${notice.bTitle }</h3></a> <br>
               	 			아이디 : ${notice.bName} &nbsp;&nbsp; 게시일 : ${notice.bDate } &nbsp;&nbsp;<br> 조회수 : ${notice.bHit }회 <br>
               	 			<%out.println("-----------------------------------------------------------------"); %>
               	 	</c:forEach>             	 		
               	 </c:otherwise>
              </c:choose>
            </div>     
          </div>
        </div>
      </div>
 </div>

    <footer class="blog-footer">
     
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
 <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>