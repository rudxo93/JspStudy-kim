<%@page import="com.kakao.web.sign.model.dto.UserDto"%>
<%@page import="com.kakao.web.sign.model.dao.SignUpDaoImpl"%>
<%@page import="com.kakao.web.sign.model.dao.SignUpDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String submit_flag = request.getParameter("submit_flag");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	SignUpDao signUpDao = new SignUpDaoImpl();
	
	if(submit_flag.equals("1")){ // 정상적인 회원가입 진행
		UserDto userDto = new UserDto(); // userDto 생성
		userDto.setUser_email(id); // parameter로 받아온 값들을 set해준다.
		userDto.setUser_password(password);
		userDto.setUser_name(name);
		userDto.setUser_phone(phone);
		
		boolean flag = signUpDao.signUp(userDto); // userDto를 signUp에 전달
		
		if(flag == true){
			response.sendRedirect("sign_in.jsp");
		}
		
	} else if(submit_flag.equals("2")){ // 전화번호 인증요청
		int flag = signUpDao.phoneNumberCheck(phone, name);
		
%>
		<jsp:forward page="sign_up_phone.jsp">
			<jsp:param value="<%=flag %>" name="flag" />
		</jsp:forward>
<%
	}
%>