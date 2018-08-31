<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<style>

</style>
</head>
<body>

<div class="container">
<table class="table">
  <thead class="thead-dark"> 
    <tr>
      <th scope="col">번호</th>
      <th scope="col">이름</th>
      <th scope="col">제목</th>
      <th scope="col">날짜</th>
      <th scope="col">조회수</th>
    </tr>
  </thead>
  <c:forEach items="${list}" var="dto" >
    <tr>
      <th scope="row">${dto.bId}</th>
      <td>${dto.bName}</td>
      <td>
      	<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
		<a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a>
      </td>
      <td>${dto.bDate}</td>
      <td>${dto.bHit}</td>
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
	      <a class="page-link" href="list.do?page=1" tabindex="-1">처음</a>
	    </li>
	    </c:when>
	    <c:otherwise>
	    	<li class="page-item">
	      	<a class="page-link" href="list.do?page=1">처음</a>
	   		</li>
	   	</c:otherwise>
	  </c:choose>
	  
	  <c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
	  	<c:choose>
	  	<c:when test="${page.curPage == fEach}">
	    <li class="page-item active">
	      <a class="page-link" href="list.do?page=${fEach}">${fEach} <span class="sr-only">(current)</span></a>
	    </li>
	    </c:when>
	    <c:otherwise>
	    <li class="page-item"><a class="page-link" href="list.do?page=${fEach}">${fEach}</a></li>
	    </c:otherwise>
	    </c:choose>
	  </c:forEach>
	  <c:choose>
	  	<c:when test="${page.curPage == page.totalPage}">
	    <li class="page-item disabled">
	      <a class="page-link" href="#" tabindex="-1">끝</a>
	    </li>
	    </c:when>
	    <c:otherwise>
	      <li class="page-item"><a class="page-link" href="list.do?page=${page.totalPage}">끝</a></li>
	  	</c:otherwise>
	  </c:choose>
	  <li><a href="write_view.do" class="badge badge-primary"><h5>글작성</h5></a></li>
	  </td>
	  </tr>
	  </ul>
	</nav>	
  </tbody>
</table>
</div>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>