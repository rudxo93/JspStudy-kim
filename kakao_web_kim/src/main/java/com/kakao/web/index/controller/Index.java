package com.kakao.web.index.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kakao.web.index.model.dto.User;
import com.kakao.web.index.service.UserService;
import com.kakao.web.index.service.UserServiceImpl;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
	public Index() {
		userService = new UserServiceImpl();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); // 기본세팅
		response.setContentType("text/html; charset=UTF-8"); // 기본세팅
		request.setCharacterEncoding("UTF-8"); // 기본세팅
		
		Cookie[] cookies = request.getCookies(); // 브라우저에 등록되어있는 모든 쿠키를 배열로 다 들고온다.
		
		if(cookies != null) {
			for (Cookie c : cookies) { // cookies에서 쿠키들을 하나씩 꺼낸다.
				if(c.getName().equals("userId")) { // 쿠키의 name이 userId라면
					HttpSession session = request.getSession(); // session 저장소를 불러와서
					User user = userService.getUser(c.getValue()); // session저장소에 user객체를 담는다.
					
					session.setAttribute("login_user", user);
				}
			}
		}
		
		request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response); // index.jsp 파일을 열겠다!
		
	}

}
