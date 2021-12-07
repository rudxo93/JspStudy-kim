<%@page import="com.kakao.web.index.model.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
	<div class="nav">
		<a href="" class="brand_logo"><h1>kakao</h1></a>
		<ul class="nav_item">
			<a href=""><li>카카오</li></a>
			<a href=""><li>뉴스</li></a>
			<a href=""><li>기술과 서비스</li></a>
			<a href=""><li>약속과 책임</li></a>
		</ul>
<%
		if(session.getAttribute("login_user") == null){
%>
		<ul class="nav_user">
			<a href="signIn"><li><i class="fas fa-user"></i></li></a>
			<a href="signUp"><li><i class="fas fa-user-plus"></i></li></a>
		</ul>
<%
		} else {
			User login_user = (User)session.getAttribute("login_user");
%>
		<ul class="nav_user">
        	<a href="#"><li><i class="fas fa-user-circle"></i><%=login_user.getId()+"@kakao.com" %></li></a>
            <a href="logout"><li><i class="fas fa-sign-out-alt"></i></li></a>
        </ul>
<%
		}
%>
	</div>
</header>