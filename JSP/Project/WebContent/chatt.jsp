<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
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
<meta name="google-signin-client_id" content="356131626574-6u6njs718ofrd2ju2bjtcqkmos8299rk.apps.googleusercontent.com">
<script>
	var pwe="";
	function submit_ajax(){
		pwe=prompt("비밀번호를 입력하세요");
	
		$.ajax({
			url:'/Project/pw_check',
			type: 'POST',
			data : {roomList:$("#roomList").val(), pw:pwe},
			dataType:'json',
			success : function(json){
				var results = eval(json);
				if(results[0].result=="ok"){
					location.href="roomIn.do?roomList="+$("#roomList").val();
				}
				else{
					alert(results[0].desc);
				}
			}			
		});				
	}
	

	
	
	function test(){
		var found = null;
		var rname = document.getElementById("roomName");
		var rn = rname.value;
		
		var rcount = document.getElementById("rCount");
		var count = rcount.value;
		
		var roomType = document.getElementsByName("rChoose");
		for(var i=0; i<roomType.length; i++) {
			if(roomType[i].checked == true)
			found = roomType[i];
		}
		if(found.value == "open")
		{
			location.href="roomCreate.do?rname="+rn+"&rcount="+count;
			
		}
		else if(found.value == "nonOpen")
		{
			var pw = prompt("비밀번호를 입력하세요.");
			location.href="pwRoom.do?rname="+rn+"&rcount="+count+"&pw="+pw;
		}
	}
		
	
	function waitList()
	{
		var wlist = [], data={};
		$.ajax({
			url:'/Project/wait',
			type: 'POST',
			dataType:'json',
			success : function(json){
				var results = eval(json);
				alert(results[0].desc);
			}			
		});	
	}
	
	</script>
	<script src = "http://code.jquery.com/jquery.js"> </script>
</head>
<body class="bg-light">
<div class="container">
	<header class="blog-header py-3">
		<div class="row flex-nowrap justify-content-between align-items-center">
        	<div class="col-4 pt-1">
          		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">방 만들기</button>
	          	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					 <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      	<div class="modal-header">
					        	<h3 class="modal-title" id="exampleModalLabel">방 설정</h3>
					        	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          		<span aria-hidden="true">&times;</span>
					        	</button>
					      	</div>
					      	<div class="modal-body">
					        	<form>
					          		<div class="form-group">
					            		<h3><label for="recipient-name" class="col-form-label">방 이름을 입력하세요</label></h3>
					            		<input type="text" class="form-control" id="roomName">
					          		</div>
					          		<div class="form-group">
					            		<h3><label for="message-text" class="col-form-label">제한인원을 입력하세요.(숫자)</label></h3>
					            		<input type="text" class="form-control" id="rCount"></textarea>
					          		</div>
					          		
					          		<div class="form-group">
					          			<h3><label for="message-text" class="col-form-label">공개방 / 비공개방</label></h3>
					          			<h5><input type="radio" name="rChoose" value="open" checked="checked">공개방</h5>
					          			<h5><input type="radio" name="rChoose" value="nonOpen">비공개방</h5>
					          		</div>
					        	</form>
					      	</div>
					      	<div class="modal-footer">
					        	<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					        	<button type="button" class="btn btn-primary" onclick="test()">방 생성</button>
					      	</div>
					    </div>
					 </div>
				</div>
	        </div>
        <div class="col-4 text-center">
            <a class="blog-header-logo text-dark" href="first.jsp">Project Web Site</a>
        </div>
        <div class="col-4 d-flex justify-content-end align-items-center">
          <%if(session.getAttribute("login") == null) {%>
          	      
            <a class="btn btn-sm btn-outline-secondary" href="join.jsp">회원가입</a> &nbsp;
            <a class="btn btn-sm btn-outline-secondary" href="login.jsp">로그인</a>       
         <%} else { %>
         	<%=session.getAttribute("id") %>님 안녕하세요! &nbsp;&nbsp;
         	
         	<%if(session.getAttribute("login").equals("google")){ %>
         	<script>
				function signOut() {
				    var auth2 = gapi.auth2.getAuthInstance();
				    auth2.signOut().then(function () {
				    	console.log('User signed out.');
				    	document.location.href="logout.do"
				    });
				}
				function onLoad() {
					gapi.load('auth2', function(){
						gapi.auth2.init();
					});
				}
			</script>
         	 <a href="#" onclick = "signOut();"class="badge badge-primary">로그아웃</a>
         	 <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
         	 
      		 <%} else if(session.getAttribute("login").equals("origin")) { %>
         	<a href = "logout.do" class="badge badge-primary">로그아웃</a>
         	<%} %>
         <%} %>
        </div>
       </div>
       
      </header>
      
		<div class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-4 pt-1">
          		<input type="hidden" id="myid" value="<%=session.getAttribute("id") %>" />
          	</div>
       		<div class="col-4 text-center">
            	<a class="blog-header-logo text-dark" href="first.jsp">대화방</a>
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
  		<thead class="thead-dark"> 
    		<tr>
      			<th scope="col">번호</th>
      			<th scope="col">방이름</th>
      			<th scope="col">방장</th>
      			<th scope="col"></th>
      			<th scope="col">공개/비공개</th>
    		</tr>
  		</thead>

  	<c:forEach items="${rlist}" var="room" >
 
  		<tr>
		   <th scope="row">${room.num}</th>
		   <td>${room.roomList}</td>
		   <td>
			   ${room.bj}
		   </td>
		   <c:choose>
		   		<c:when test="${room.ox eq '공개방' }">		   
		   			<td><a href="roomIn.do?roomList=${room.roomList}" class="badge badge-primary"><h5>방 참여</h5></a></td>				
		   		</c:when>
		   		<c:otherwise>	
		   		  		
		   			<input type="hidden" id="roomList" name="roomList" value="${room.roomList}">
		   			<td><a href="#" class="badge badge-primary" onclick="submit_ajax()"><h5>방 참여</h5></a></td>
		   		 
		   		</c:otherwise>
		   </c:choose>
		   <td>${room.ox}</td>
		</tr>
  	
    </c:forEach>

	<tbody>
		<tr>
			<td colspan="5" >
				<nav aria-label="..." class="nav justify-content-center">
	  				<ul class="pagination" id="nav">	  
	  					<c:choose>
							<c:when test="${(page.curPage - 1) < 1}">
	    						<li class="page-item disabled" >
	      							<a class="page-link" href="chatt.do?page=1" tabindex="-1">처음</a>
	    						</li>
	    					</c:when>
	    					<c:otherwise>
	    						<li class="page-item">
	      							<a class="page-link" href="chatt.do?page=1">처음</a>
	   							</li>
	   						</c:otherwise>
	  					</c:choose>
	  
	  					<c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
	  						<c:choose>
	  							<c:when test="${page.curPage == fEach}">
	    							<li class="page-item active">
	      								<a class="page-link" href="chatt.do?page=${fEach}">${fEach} <span class="sr-only">(current)</span></a>
	    							</li>
	    						</c:when>
	    						<c:otherwise>
	    							<li class="page-item"><a class="page-link" href="chatt.do?page=${fEach}">${fEach}</a></li>
	    						</c:otherwise>
	    					</c:choose>
	  					</c:forEach>
	  					<c:choose>
	  						<c:when test="${page.curPage == page.totalPage}">
	    						<li class="page-item disabled">
	      							<a class="page-link" href="#" tabindex="-1">끝</a><br>
	    						</li>
	    					</c:when>
	    				<c:otherwise>
	      					<li class="page-item"><a class="page-link" href="chatt.do?page=${page.totalPage}">끝</a></li><br>
	  					</c:otherwise>
	  					</c:choose>
					</ul>
				</nav>
				
	  		</td>
	  	</tr>
	  		 	
  	</tbody>
	</table>
	<div class="row mb-2" >
        <div class="col-md-12" >
           <div class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-8" style="overflow:auto;" id="div">
			<textarea id="echoText" style="width:600px;height:300px;"></textarea>
			</div>	
			<div class="col-4">
          		<strong class="d-inline-block mb-2 text-success">현재 접속 인원</strong>
            	<div id="myWait" style="width:300px;height:250px;background-color:white"></div>         		
        	</div>	
        	
		</div>
				   
        	</div>
        
			<div class="col-12">
				<form onsubmit="return false">
					<input id="message" type="text" style="width:760px; height:30px;" onkeydown="Enter(event)">
					<input onclick="wsSendMessage();" id="trans" value="전송" type="button">
					<input onclick="wsCloseConnection();" value="Disconnect" type="button">
					<br>
				</form>
			</div>
      </div>
      <div>
	  	<button type="button" class="btn btn-secondary" id="awlist" value="Awlist" onclick="awList()">접속 리스트</button>
	  	<button type="button" class="btn btn-info" id="noMsg" value="nomsg:" onclick="noMsg()">금칙어</button>
	  	<button type="button" class="btn btn-warning" id="allMsg" value="All:"onclick="allMessage()">공지사항</button>
	  	<button type="button" class="btn btn-light" id="Ears" value="ear:" onclick="Ear()">귓속말</button>
	  </div>
	<script type="text/javascript">
		
		function Enter(e){
			if(e.which == 13){
				wsSendMessage();
			}
		}
		
		document.getElementById("echoText").scrollTop = document.getElementById("echoText").scrollHeight;

		var id = document.getElementById("myid");
		
		var webSocket = new WebSocket("ws://localhost:8081/Project/websocketendpoint2");
		var echoText = document.getElementById("echoText");
		echoText.value = "";
		var message = document.getElementById("message");
		webSocket.onopen = function(message){ wsOpen(message);};
		webSocket.onmessage = function(message){ wsGetMessage(message);};
		webSocket.onclose = function(message){ wsClose(message);};
		webSocket.onerror = function(message){ wsError(message);};
		
		function wsOpen(message){
			webSocket.send(id.value+"|"+"님이 대기실에 입장하셨습니다.");
			echoText.value += "Connected ... \n";
		}
		
		function wsSendMessage(){
			webSocket.send( id.value+ "|"+message.value);
			message.value = "";
		}
		
		function awList(){// 내방 대기실 인원
			var wlist = document.getElementById("awlist");
			webSocket.send(id.value+ "|"+"/"+wlist.value)
		}
		
		function allMessage(){// 공지사항
			var allmsg = document.getElementById("allMsg");
			webSocket.send( id.value+ "|"+allmsg.value+ message.value);
			message.value = "";
		}	
		
		function noMsg(){ // 금칙어
			var nonMsg = document.getElementById("noMsg");
			webSocket.send(id.value+ "|"+nonMsg.value+ message.value)
		}
		
		function Ear(){ // 귓속말	
			var Ears = document.getElementById("Ears");
			var all = new Array();
			var allmem = document.getElementsByName("allMember");
			for(var i = 0; i < allmem.length; i++)
			{
				if(allmem[i].checked)
					webSocket.send(id.value+ "|"+ Ears.value + allmem[i].value + ":" + message.value);
			}

		}
		
		function wsCloseConnection(){
			webSocket.close();
		}
		
		function wsGetMessage(message){
			var roomlist = message.data;
			var order = roomlist.substring(0,7);
			var realmsg = roomlist.substring(7);
			
		
			if(order == "/alList") // 대기실 인원
			{
				document.getElementById("myWait").innerHTML = realmsg;
			}
			else if(order == "/ivnYou") //초대 받기
			{
				var response = confirm(realmsg+"님이 당신을 초대하셨습니다.");
				if(response == true)
				{
					webSocket.send(id.value+ "|" + "ivIn:" + realmsg);
				}
				else
				{
					webSocket.send(id.value+ "|" + "ivNo:" + realmsg)
				}
			}
			
			else if(order == "/invite")
			{
				var ivin = realmsg.split(',');
				location.href="chattClient.jsp?rn="+ivin[0]+"&id="+ivin[1];
			}
			else if(message.data == "Full")
			{
				alert("방이 가득 찼습니다.");
			}
			
			else
			{
				echoText.value += message.data + "\n";
			}
			
			
		}
		
		function wsclose(message){
			webSocket.send(id.value+"|"+"님이 사라지셨습니다.");
			echoText.value += "Disconnect ... \n";
		}
		
		function wserror(message){
			echoText.value += "Error ... \n";
		}
	</script>
</div>
<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>