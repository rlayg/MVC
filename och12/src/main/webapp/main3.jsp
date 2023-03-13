<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="memberCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 부트스트랩 적용 안한 main -->
	<div id="header">
		<h5>Loging Success</h5>
		<h2>회원관리 메뉴</h2>
		<%=id %>님 환영합니다><p>
	</div>
	<div id="contents" >
		<div class="btn-group" >
		  <a href="logOut.jsp" 		class="btn btn-primary">로그아웃</a>
		  <a href="joinForm.jsp" 	class="btn btn-primary">회원가입</a>
		  <a href="joinForm.jsp" 	class="btn btn-primary">회원가입(image)</a>
		  <a href="joinForm3.jsp" 	class="btn btn-primary">회원명단</a>
		  <a href="memberList.jsp" 	class="btn btn-primary">회원정보수정</a>
		  <a href="updateForm3.jsp" class="btn btn-primary">회원정보수정(image)</a>
		  <a href="deleteForm.jsp" 	class="btn btn-primary">회원탈퇴</a>
		  
		</div>
			
	</div>
	<div id="footer">
		<h2>푸터</h2>
		<p> 모든 저작권은 저에게 있습니다</p>
		<p> 02-1234-1234 </p>
	</div>
</body>
</html>