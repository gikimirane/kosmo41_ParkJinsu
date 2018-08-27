<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
	<script src = "http://code.jquery.com/jquery.js"> </script>
	<script>
		function form_check() {
			submit_ajax();
		}
		
		function submit_ajax(){		
			var queryString = $("#LogInProcess").serialize();
			
			$.ajax({
				url:'/Jsp19_2/LogInProcess',
				type: 'POST',
				data : queryString,
				dataType:'json',
				success : function(json){
					var results = eval(json);
					if(results[0].result=="ok"){
						alert("정상적으로 로그인되었습니다.");
						window.location.replace("LoginResult.jsp");
					}
					else{
						alert(results[0].desc);
						window.location.replace("login.jsp");
					}
				}			
			});
				
		}
	</script>
</head>
<body>

	<form name="LogInProcess" id="LogInProcess">
		아이디 : <input type="text" name="id"><br>
		비밀번호 : <input type="password" name="pw"><br>
		<input type="button" value="로그인" onclick="form_check()"> &nbsp;&nbsp;
		<a href="join.jsp">회원가입</a>
		
	</form>
</body>
</html>