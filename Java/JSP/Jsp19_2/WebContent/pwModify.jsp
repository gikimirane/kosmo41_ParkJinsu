<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 수정</title>
	<script src = "http://code.jquery.com/jquery.js"> </script>
	<script>
		function submit_ajax(){		
			var queryString = $("#PwProcess").serialize();
			
			$.ajax({
				url:'/Jsp19_2/PwProcess',
				type: 'POST',
				data : queryString,
				dataType:'json',
				success : function(json){
					var results = eval(json);
					if(results[0].result=="ok"){
						alert("정상적으로 변경되었습니다.");
						window.location.replace("LoginResult.jsp");
					}
					else if(results[0].result=="fail1"){
						alert(results[0].desc);
						window.location.replace("pmModify.jsp");
					}
					else if(results[0].result=="fail2"){
						alert(results[0].desc);
						//window.location.replace("pmModify.jsp");
					}
				}			
			});
				
		}
	</script>
</head>
<body>
	<form name="PwProcess" id="PwProcess">
		현재 비밀번호 : <input type="password" name="pw" size="10"> <br><br>
		
		변경된 비밀번호 : <input type="password" name="pw1" size="10"> <br><br>
		비밀번호 확인 : <input type="password" name="pw2" size="10"><br>
		<input type="button" value="확인" onclick="submit_ajax()">
	</form>
</body>
</html>