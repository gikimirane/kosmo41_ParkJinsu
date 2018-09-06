<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script async defer src="https://apis.google.com/js/api.js" onload="this.onload=function(){};HandleGoogleApiLibrary()" onreadystatechange="if (this.readyState === 'complete') this.onload()"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function HandleGoogleApiLibrary() {
	// Load "client" & "auth2" libraries
	gapi.load('client:auth2',  {
		callback: function() {
			// Initialize client & auth libraries
			gapi.client.init({
		    	apiKey: 'YOUR_GOOGLE_API_KEY',
		    	clientId: 'YOUR_GOOGLE_API_CLIENT_ID',
		    	scope: 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/plus.me'
			}).then(
				function(success) {
			  		// Libraries are initialized successfully
		  			// You can now make API calls
				}, 
				function(error) {
					// Error occurred
					// console.log(error) to find the reason
			  	}
			);
		},
		onerror: function() {
			// Failed to load libraries
		}
	});
	$("#google-login-button").on('click', function() {
		// API call for Google login
		gapi.auth2.getAuthInstance().signIn().then(
			function(success) {
				// Login API call is successful	
			},
			function(error) {
				// Error occurred
				// console.log(error) to find the reason
			}
		);
	});
	
}


</script>
</head>
<body>

</body>
</html>