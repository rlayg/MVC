<%@page import="och12.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!-- 
 	Service (Model2 ==> Java Conversion) 여긴 모델1 여기가 서비스부분
 	Service는 비지니스 업무
   -->

<%
	request.setCharacterEncoding("utf-8");	

	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	System.out.println("id ->" + id);
	System.out.println("passwd ->" + passwd);
	
	MemberDao memberDao = MemberDao.getInstance(); 
	int result = memberDao.check(id, passwd);
	// 존재하는 User ==> PreparedStatement 사용
	if (result == 1) {
		session.setAttribute("id", id);
		response.sendRedirect("main.jsp");
		//Password X
	} else if (result == 0){
%>
	<script type="text/javascript">
		alert("암호 몰라 !!");
		history.go(-1);
	</script> 
<% }else { %>
	<script type="text/javascript">
		alert("User 미존재(-1) 넌 누구냐");
		history.go(-1);
	</script>
<% } %> 
</body>
</html>
