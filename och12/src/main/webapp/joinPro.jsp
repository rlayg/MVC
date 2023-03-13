<%@page import="och12.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Service(buz역할)</title>
</head>
<body>
<jsp:useBean id="member" class="och12.MemberDto"></jsp:useBean>
<jsp:setProperty property="*" name="member"/>
<%
	MemberDao md = MemberDao.getInstance();
//	HW1 insert
	int result = md.insert(member);
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