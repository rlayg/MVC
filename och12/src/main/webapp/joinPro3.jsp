<%-- <%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
	request.setCharacterEncoding("utf-8");
	int maxSize = 15 * 1024 * 1024; //15메가
	String fileSave = "/fileSave";
	//Meta Data
	String realPath = getServletContext().getRealPath(fileSave);
	System.out.println("realPath -> " + realPath);
	
	//UpLoad
	MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
	Enumeration en = multi.getFileNames();
	//서버에 저장된 파일 이름
	String serverSaveFilename = "";
	while(en.hasMoreElements()) {
		//input 태그의 속성이 file인 태그의 name 속성값: 파라미터이름
		String parameterName = (String) en.nextElement();
		//서버에 저장된 파일 이름
		serverSaveFilename = multi.getFilesystemName(parameterName);
		//전송전 원래의 파일 이름
		String original = multi.getOriginalFileName(parameterName);
		//전송된 파일의 내용 타입
		String type = multi.getContentType(parameterName);
		//전송된 파일속성이 file인 태그의 name 속성값을 이용해 파일객체 생성
		File file = multi.getFile(parameterName);
		System.out.println("real Path : " + realPath +"<br>" );
		System.out.println("파라미터 이름 : " + parameterName +"<br>");
		System.out.println("실제 파일 이름 : " + original +"<br>");
		System.out.println("저장된 파일 이름 : " + serverSaveFilename +"<br>");
		System.out.println("파일 타입 : " + type  +"<br>");
		if(file != null){
			System.out.println("크기 : " + file.length()  +"<br>");
		}
	}
	String id = multi.getParameter("id");
	String name = multi.getParameter("name");   //이미지가 같이 올라갈때는 multi로 불러라
	String passwd = multi.getParameter("passwd");
	String address = multi.getParameter("address");
	String tel = multi.getParameter("tel");
	String img_path = serverSaveFilename;
	System.out.println("id -> " + id);
	System.out.println("passwd -> " + passwd);

	//DTO 작업
	MemberDto memberDto = new MemberDto();
	memberDto.setId(id);
	memberDto.setName(name);
	memberDto.setPasswd(passwd);
	memberDto.setAddress(address);
	memberDto.setTel(tel);
	memberDto.setImg_path(img_path);
	
	
	
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

 --%>

<%@page import="och12.MemberDto"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
	request.setCharacterEncoding("utf-8");
	//      5M
	int maxSize = 5 * 1024 * 1024;
	String fileSave = "/fileSave";

	// Meta Data 
	String realPath = getServletContext().getRealPath(fileSave);
	System.out.println("realPath->" + realPath);
	MultipartRequest multi = 
 	new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
	Enumeration en = multi.getFileNames();
	// 서버에 저장된 파일 이름
	String serverSaveFilename = "";
	while (en.hasMoreElements()) {
		//input 태그의 속성이 file인 태그의 name 속성값 :파라미터이름
		String parameterName = (String) en.nextElement();
		//서버에 저장된 파일 이름 
		serverSaveFilename = multi.getFilesystemName(parameterName);
		//전송전 원래의 파일 이름 
		String original = multi.getOriginalFileName(parameterName);
		//전송된 파일의 내용 타입 
		String type = multi.getContentType(parameterName);
		//전송된 파일속성이 file인 태그의 name 속성값을 이용해 파일객체생성 
		File file = multi.getFile(parameterName);
		out.println("real Path : " + realPath + "<br>");
		out.println("파라메터 이름 : " + parameterName + "<br>");
		out.println("실제 파일 이름 : " + original + "<br>");
		out.println("저장된 파일 이름 : " + serverSaveFilename + "<br>");
		out.println("파일 타입 : " + type + "<br>");
		if (file != null) {
			out.println("크기 : " + file.length() + "<br>");
		}
	}
	String id 		 = multi.getParameter("id");
	String name      = multi.getParameter("name");
	String passwd    = multi.getParameter("passwd");
	String address   = multi.getParameter("address");
	String tel       = multi.getParameter("tel");
	String img_path  = serverSaveFilename;

	// DTO 작업 
	MemberDto member = new MemberDto();
	member.setId(id);
	member.setName(name);
	member.setPasswd(passwd);
	member.setAddress(address);
	member.setTel(tel);
	member.setImg_path(img_path);


	MemberDao md = MemberDao.getInstance();
	int result = md.insert3(member);   
	if (result > 0) { 
%>
	<script type="text/javascript">
		alert("회원가입(image) 축하 !! 이제 고생 좀 해");
		location.href="loginForm.jsp";
	</script>
<%
	} else { %>
		<script type="text/javascript">
			alert("헐 실패야 똑바로 해 ~");
			location.href="joinForm.jsp";
		</script>
<%	}  %>
</body>
</html>

