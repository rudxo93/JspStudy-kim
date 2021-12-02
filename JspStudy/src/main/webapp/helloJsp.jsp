<%@page import="com.web.dao.UserDaoImpl"%>
<%@page import="com.web.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String name = null;
	UserDao userDao = new UserDaoImpl();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	name = userDao.getUserName("kim", "1234");
	name = name + "님 환영합니다.";
	int flag = userDao.login("kim", "1234");
	String msg = null;
	if(flag == 0){
		msg = "존재하지 않는 아이디입니다. 가입 후 이용바랍니다.";
	} else if(flag == 1){
		msg = "비밀번호가 일치하지 않습니다. 다시 확인해 주시요.";
	} else if(flag == 2) {
		msg = "로그인 성공!";
	} else {
		msg = "DB오류!";
	}
%>
	<h1>Hello Jsp!</h1>
	<h4><%=name %></h4>
	<h2><%=msg %></h2>
</body>
</html> 