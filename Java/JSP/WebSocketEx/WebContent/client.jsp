<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id == null) {
	%>
		<jsp:forward page="login.jsp" />
	<%
		}
		else 
		{				
			session.setAttribute("uid", id);
		}
	%>
	<div>
	
	사용자 아이디 : <%=id %>
	
	</div>
	<div>
		<input type="text" id="messageinput"/>
	</div>
	<div>
		<button type="button" onclick="openSocket();">Open</button>
		<button type="button" onclick="send();">Send</button>
		<button type="button" onclick="closeSocket();">Close</button>
	</div>
	<!-- Server responses get written here -->
	<div id="messages"></div>
	
	<!-- Script to utilise the WebSocket -->
	<script type="text/javascript">
		var webSocket;
		var messages = document.getElementById("messages");
		
		function openSocket(){
			// Ensures only one connection is open at a time
			if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED) {
				writeResponse("WebSocket is already opened.");
				return;
			}
			
			// Create a new instance of the websocket
			//webSocket = new WebSocket("ws://localhost/ *ProjectName*  /echo");
			webSocket = new WebSocket("ws://localhost:8081/WebSocketEx/websocketendpoint2"); //서버 접속
			
			/**
			 * Binds functions to the listeners for the websocket.
			 */
			 webSocket.opopen = function(event) { //on : ~~ 할때
				 // For reasons I can't determine, onopen gets called twice
				 // and the first time event.data is undefined.
				 // Leave a comment if you know the answer.
				 if(event.data === undefined)
					 return;
				 
				 writeResponse(event.data);
			 };
			 
			 webSocket.onmessage = function(event) { // 메시지가 나에게 들어왔을때
				 writeResponse(event.data);
			 };
			 
			 webSocket.onclose = function(event) {
				 writeResponse("Connection closed");
			 };
		}
		
		/**
		 * Sends the value of the text input to the server	
		*/
		
		function send() { // 보낸것
			var id = "<%= id %>";
			var text = document.getElementById("messageinput").value;
			webSocket.send(id + "|" + text);
		}
		
		function closeSocket(){
			webSocket.close();
		}
		
		function writeResponse(text) { //데이터가 나에게 왔을때
			messages.innerHTML += "<br/>" + text;
		}
		//데이터 길어질 시 스크롤 생성 및 오래된 데이터 없어지게 하기
	</script>
</body>
</html>