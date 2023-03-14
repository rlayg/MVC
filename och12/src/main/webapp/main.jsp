<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="memberCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
	<div id="header">
		<h5>Loging Success</h5>
		<h2>회원관리 메뉴</h2>
		<%=id %>님 환영합니다><p>
	</div>
	<div id="contents" >
		<div class="btn-group" >
		  <a href="logOut.jsp" 		class="btn btn-primary">로그아웃</a>
		  <a href="joinForm.jsp" 	class="btn btn-primary">회원가입</a>
		  <a href="joinForm3.jsp" 	class="btn btn-primary">회원가입(image)</a>
		  <a href="memberList.jsp" 	class="btn btn-primary">회원명단</a>
		  <a href="updateForm.jsp" 	class="btn btn-primary">회원정보수정</a>
		  <a href="updateForm3.jsp" class="btn btn-primary">회원정보수정(image)</a>
		  <a href="deleteForm.jsp" 	class="btn btn-primary">회원탈퇴</a>
		  
		</div>
		<div>
			<img alt="회원이미지" src="image/Image20230314091510.jpg">
		</div>	
	</div>
	<div id="footer">
		<h2>푸터</h2>
		<p> 모든 저작권은 저에게 있습니다</p>
		<p> 02-1234-1234 </p>
	</div>
</body>
</html>