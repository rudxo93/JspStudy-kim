package com.kakao.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); // 기본세팅
		response.setContentType("text/html; charset=UTF-8"); // 기본세팅
		request.setCharacterEncoding("UTF-8"); // 기본세팅
		
		request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response); // index.jsp 파일을 열겠다!
//		request.getRequestDispatcher("index.jsp").include(request, response);
		
//		response.sendRedirect("index.jsp");
	}

}
