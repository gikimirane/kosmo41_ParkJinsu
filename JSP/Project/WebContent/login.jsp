<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery.js"></script>

</script>
<script>
	function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		var gid = profile.getId();
		var gMail = profile.getEmail();
		var gName = profile.getName();
		
		document.frm_glogin.action="glogin.do?gMail="+gMail+"&gName="+gName;
		document.frm_glogin.method="post";
		document.frm_glogin.submit();
		
	}
	function onFailure(error) {
	}

	function renderButton() {
	    gapi.signin2.render('my-signin2', {
	        'scope': 'profile email',
	        'width': 300,
	        'height': 50,
	        'longtitle': true,
	        'theme': 'dark',
	        'onsuccess': onSignIn,
	        'onfailure': onFailure
	        
	    });
	}
	

</script>

<meta name="google-signin-client_id" content="356131626574-6u6njs718ofrd2ju2bjtcqkmos8299rk.apps.googleusercontent.com">
</head>

<body class="text-center">

	<form name = frm_glogin></form>
	
    <form action="loginOk.do" class="form-signin" name="frm_login">
      <img class="mb-4" type="button" src="./pictures/로고.jpg" onclick="javascript:window.location='first.jsp'" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">로그인 Please!</h1>
      <label for="inputID" class="sr-only">ID</label>
      <input type="text" name="id" id="inputEmail" class="form-control" placeholder="ID" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="pw" id="inputPassword" class="form-control" placeholder="Password" required>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
      <button class="btn btn-lg btn-primary btn-block" onclick="javascript:window.location='join.jsp'">회원가입</button>
      <br>
      <div id="my-signin2"></div>

		<!-- /container -->
	<script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


	</script>
      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
  <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
  </body>
</html>
