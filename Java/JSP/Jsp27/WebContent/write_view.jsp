<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<!--  <script src="https://cdn.ckeditor.com/4.10.0/standard/ckeditor.js"></script>-->
<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script>
function form_check() {

	 // 에디터의 내용이 textarea에 적용된다.
	 oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
	 document.write_form.submit();
}
</script>

</head>
<body>

	<table width="0" cellpadding="0" cellspacing="0" border="1">
		<form name="write_form" action="write.do" method="post">
			<tr>
				<td>이름</td>
				<td> <input type="text" name="bName" size = "50"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td> <input type="text" name="bTitle" size = "50"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td> 
					<textarea name="bContent" id="ir1" rows="10" cols="80">
                		This is my textarea to be replaced with CKEditor.
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
           	 	<!-- ck 에디터<script>
                // Replace the <textarea id="editor1"> with a CKEditor
                // instance, using default configuration.
                CKEDITOR.replace( 'editor1' );
                </script> -->
              
				</td>
			</tr>
			<tr>
				<td colspan="2">  
				<a href="javascript:form_check();">입력</a> &nbsp;&nbsp;
				<a href="list.do?page=1">목록보기</a>
				</td>
			</tr>
		</form>
	</table>
</body>
</html>