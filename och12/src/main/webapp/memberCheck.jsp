<%
	String id = (String)session.getAttribute("id");
	if(id == null || id.equals("")){
		response.sendRedirect("loginForm.jsp");
	}
	/* 세션에 로그인 정보 없으니까 로그인 부터 하라는 뜻 */
%>

