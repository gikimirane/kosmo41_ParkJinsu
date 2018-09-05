<%@page import="com.study.jsp.BDto" %>
<%@page import="com.study.jsp.BDao" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 	if(session.getAttribute("ValidMem") == null) {
 %>
	<jsp:forward page="login.jsp" />
<%
	}
%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String id = (String)session.getAttribute("id");
	BDao dao = BDao.getInstance(); 
	BDto dto = dao.notice(id,request,response);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<!--  <script src="https://cdn.ckeditor.com/4.10.0/standard/ckeditor.js"></script>-->
<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

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
</style>
</head>
<body>

<table class="table">
	<form name="write_form" action="write.do" method="post">
		<tr>
			<div class="container">
				<br>
				<a href="javascript:form_check();">입력</a> &nbsp;&nbsp;
				<a href="notice.do?page=1">목록보기</a><br><br>
			</div>
		</tr>
		<tr>
			<div class="container">
				<div class="input-group mb-3">
	 				<input type="text" class="form-control" name="bName" placeholder="이름" aria-label="Username" aria-describedby="basic-addon1">
				</div>
			</div>
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