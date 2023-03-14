<%@page import="och12.MemberDto"%>
<%@page import="och12.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%	
	//HW2 
	//1. 이미지 업로드(힌트: och14: upload.jsp)
	//2. 각각의 column 가져오기 --> DTO에 셋팅


	//DTO 작업
	MemberDto memberDto = new MemberDto();
	MemberDao md = MemberDao.getInstance();
//	HW1 insert
	int result = md.insert3(memberDto);
	if(result > 0){
%>
	<script type="text/javascript">
		alert("회원가입 축하! 이제 고생 좀 해")
		location.href="loginForm.jsp";
	</script>
<%
	} else {
%>	
	<script type="text/javascript">
		alert("헐 실패야 다시 해");
		location.href="joinForm.jsp";
	</script>
<% } %>
</body>
</html>