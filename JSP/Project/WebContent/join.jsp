<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%session.setAttribute("bUrl", "first.do"); %>
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
<script>
	function frm_submit() {
		document.frm_search.submit();
	}
	function frm_submit2() {
		document.check.submit();
	}
</script>
</head>
<body class="bg-light">
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
      </header>

      <div class="nav-scroller py-1 mb-2">
        <nav class="nav d-flex justify-content-between">
          	<a class="p-2 text-muted" href="notice.do">공지사항</a>
          	<a class="p-2 text-muted" href="chatt.do">대화방</a>
          	<a class="p-2 text-muted" href="list.do">자유 게시판</a>
          	<a class="p-2 text-muted" href="picture.do">사진 게시판</a>
          	<a class="p-2 text-muted" href="modify.jsp">내 정보</a>         
        </nav>
      </div>
      <br>
      <div class="text-center">
        
        <h2>회원가입!</h2>
        <p class="lead">회원가입을 환영합니다!</p>
      </div>

      <div class="row">
        
        <div class="col-md-12 order-md-1">
          <h4 class="mb-3">정보를 입력하세요</h4>
          <form action="joinOk.do" class="needs-validation" name="reg_frm" novalidate>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="firstName">이름</label>
                <input type="text" class="form-control" name="name" id="firstName" placeholder="이름" value="" required >
                <div class="invalid-feedback">
                  이름을 입력하세요
                </div>
              </div>             
            </div>

            <div class="mb-3">
              <label for="username">Id</label>
              <div class="input-group">
               
                <input type="text" class="form-control" name="id" id="username" placeholder="Username" required>
                <div class="invalid-feedback" style="width: 100%;">
                  아이디를 입력하세요
                </div>
              </div>
            </div>
            
            <div class="mb-3">
              <label for="Password">비밀번호</label>
              <input type="password" class="form-control" name="pw" id="pw" placeholder="비밀번호" required>
              <div class="invalid-feedback">
                비밀번호를 입력하세요.
              </div>
            </div>
            
            <div class="mb-3">
              <label for="Password_Check">비밀번호 확인</label>
              <input type="password" class="form-control" name="pw_check" id="pw_check" placeholder="비밀번호 확인" required>
              <div class="invalid-feedback">
                비밀번호가 틀립니다.
              </div>
            </div>

            <div class="mb-3">
              <label for="email">Email</label>
              <input type="email" class="form-control" name="eMail" id="email" placeholder="E-mail" required>
              <div class="invalid-feedback">
                E-mail을 입력하세요.
              </div>
            </div>

            <div class="mb-3">
              <label for="address">주소</label>
              <input type="text" class="form-control" name="address" id="address" placeholder="주소">
            </div>
                 
            <hr class="mb-4">
            <button class="btn btn-primary btn-lg btn-block" type="submit" onclick="infoConfirm()">가입하기</button>
            <button class="btn btn-primary btn-lg btn-block" type="submit" onclick="javascript:window.location='login.jsp'">로그인</button>
          </form>
        </div>
      </div>

      <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; Project Web Site</p>
      </footer>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="http://getbootstrap.com/docs/4.1/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="http://getbootstrap.com/docs/4.1/assets/js/vendor/popper.min.js"></script>
    <script src="http://getbootstrap.com/docs/4.1/dist/js/bootstrap.min.js"></script>
    <script src="http://getbootstrap.com/docs/4.1/assets/js/vendor/holder.min.js"></script>
    <script>
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      (function() {
        'use strict';

        window.addEventListener('load', function() {
          // Fetch all the forms we want to apply custom Bootstrap validation styles to
          var forms = document.getElementsByClassName('needs-validation');

          // Loop over them and prevent submission
          var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
              if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
              }
              form.classList.add('was-validated');
            }, false);
          });
        }, false);
      })();
    </script>     

</body>
</html>