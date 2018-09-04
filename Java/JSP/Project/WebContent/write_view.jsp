<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<!--  <script src="https://cdn.ckeditor.com/4.10.0/standard/ckeditor.js"></script>-->
<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script language="JavaScript" src="members.js"></script>

<script>
function form_check() {

	 // 에디터의 내용이 textarea에 적용된다.
	 oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
	 document.write_form.submit();
}
</script>


<style>
	#ir1 {
		resize:none;
		width:100%;
		
		border:1px solid #ccc;
	}
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

<table class="table">
	<form name="write_form" action="write.do" method="post">
		<tr>
			<div class="container">
				<br>
				<a href="javascript:form_check();">입력</a> &nbsp;&nbsp;
				<a href="list.do?page=1">목록보기</a><br><br>
			</div>
		</tr>
		<tr>
			<input type="hidden" name="bName" value="<%=session.getAttribute("id")%>">
		</tr>
		
		<tr>
			<div class="container">
				<div class="input-group mb-3">
	  				<input type="text" class="form-control" name="bTitle" placeholder="제목" aria-label="Recipient's username" aria-describedby="basic-addon2">
				</div>
			</div>
		</tr>
		<tr>
		<div class="container">
		<div class="input-group">
	  		<textarea class="form-control" rows="20" name="bContent" id="ir1" aria-label="With textarea"></textarea>
			<script type="text/javascript">
			var oEditors = [];
			nhn.husky.EZCreator.createInIFrame({
				oAppRef: oEditors,
				 elPlaceHolder: "ir1",
				sSkinURI: "./naver-editor/SmartEditor2Skin.html",
				 fCreator: "createSEditor2"
			});
						
			</script>  
		</div>
		</div>
		</tr>
	</form>
</table>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>