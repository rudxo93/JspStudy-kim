package com.kakao.web.sign.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakao.web.sign.model.dto.UserDto;
import com.kakao.web.sign.service.SignUpService;
import com.kakao.web.sign.service.SignUpServiceImpl;

@WebServlet("/signUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SignUpService signUpService;
	
	public SignUp() {
		signUpService = new SignUpServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  // 인코딩
		 
		request.getRequestDispatcher("WEB-INF/views/sign_up_email.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String submitStatus = request.getParameter("submit_status");
		
		if(submitStatus.equals("email")) { // email이냐? email일때만 eamil에 관련된 작업을 한다.
			 // 가입된 아이디 인지 확인해야한다.
			String id = request.getParameter("id");
			
			int flag = signUpService.idCheck(id); // idcheck의 결과값을 담아준다.
			request.setAttribute("id", id); // flag가 0이든 1이든 setAttribute되어있다.
			
			if(flag == 1){ // 아이디가 이미 db에 존재한다.
				request.setAttribute("flag", flag);
				request.getRequestDispatcher("WEB-INF/views/sign_up_email.jsp").forward(request, response);
			} else { // 존재하지 않는 경우
				request.getRequestDispatcher("WEB-INF/views/sign_up_password.jsp").forward(request, response); // 다음 페이지로 넘기기
			}
		} else if(submitStatus.equals("password")) { // 어차피 js에서 넘긴다
			request.getRequestDispatcher("WEB-INF/views/sign_up_repassword.jsp").forward(request, response);
			
		} else if(submitStatus.equals("repassword")) {
			request.getRequestDispatcher("WEB-INF/views/sign_up_name.jsp").forward(request, response);
			
		} else if(submitStatus.equals("name")) {
			request.getRequestDispatcher("WEB-INF/views/sign_up_phone.jsp").forward(request, response);
			
		} else if(submitStatus.equals("phone")) {
			String submitFlag = request.getParameter("submit_flag");
			
			if(submitFlag.equals("1")) { //flag가 1이라면? 가입요청(마침 버튼)
				UserDto userDto = new UserDto();
				userDto.setUser_email(request.getParameter("id"));
				userDto.setUser_password(request.getParameter("password"));
				userDto.setUser_name(request.getParameter("name"));
				userDto.setUser_phone(request.getParameter("phone"));
				
				boolean signUpFlag = signUpService.signUp(userDto);
				if(signUpFlag == true) {
					response.sendRedirect("signIn");
				}else {
					request.getRequestDispatcher("WEB-INF/views/sign_up_phone.jsp").forward(request, response);
				}
				
			}else if(submitFlag.equals("2")) { // 전화번호 인증요청
				String phone = request.getParameter("phone");
				String name = request.getParameter("name");
				if(phone != null && name != null) { // parameter값이 null이 아닐때
					int flag = signUpService.phoneNumberCheck(phone, name); // flag값 전달받음
					request.setAttribute("flag", flag); // flag값 전달
					// 다시 phone으로 넘겨준다.
					request.getRequestDispatcher("WEB-INF/views/sign_up_phone.jsp").forward(request, response);
				}
				
			}
			
		} else {
			System.out.println("접속오류!!(잘못된 접근입니다)");
		}
	}
}
