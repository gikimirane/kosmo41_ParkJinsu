<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- ck에디터 <script src="https://cdn.ckeditor.com/4.10.0/standard/ckeditor.js"></script> -->
<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script>
	function form_check(){
		document.modify_form.submit();
	}
</script>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form name="modify_form" action="modify.do" method="post">
			<input type="hidden" name="bId" value="${content_view.bId}">
			<tr>
				<td>번호</td>
				<td>${content_view.bId}</td>
			</tr>
			<tr>
				<td>히트</td>
				<td>${content_view.bHit}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td> <input type="text" name="bName" value="${content_view.bName}"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td> <input type="text" name="bTitle" value="${content_view.bTitle}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td> 
					
					<textarea name="bContent" id="ir1" rows="10" cols="100">             		
                		${content_view.bContent}
           			 </textarea>
           			 <script type="text/javascript">
						var oEditors = [];
						nhn.husky.EZCreator.createInIFrame({
						    oAppRef: oEditors,
						    elPlaceHolder: "ir1",
						    sSkinURI: "./naver-editor/SmartEditor2Skin.html",
						    fCreator: "createSEditor2"
						});
					 </script>
           	 		<!-- ck 에디터
           	 		 <script>
	                // Replace the <textarea id="editor1"> with a CKEditor
	                // instance, using default configuration.
	                CKEDITOR.replace( 'editor1' );-->
	                </script>
				</td>
			</tr>
			<tr>
				<td colspan="2"> 

				<a href="javascript:form_check();">수정완료</a> &nbsp;&nbsp;
				<a href="content_view.do?bId=${content_view.bId}">취소</a>&nbsp;&nbsp;
				<a href="list.do?page=<%=session.getAttribute("cpage")%>">목록보기</a> &nbsp;&nbsp;
				
			</tr>
		</form>
	</table>
</body>
</html>