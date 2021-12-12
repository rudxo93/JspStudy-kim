package com.kakao.web.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakao.web.notice.model.dto.NoticeDto;
import com.kakao.web.notice.service.NoticeService;
import com.kakao.web.notice.service.NoticeServiceImpl;


@WebServlet("/notice-update")
public class NoticeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeService noticeService;
	
    public NoticeUpdate() {
    	noticeService = new NoticeServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		request.setAttribute("notice", noticeService.getNotice(code));
		request.getRequestDispatcher("/WEB-INF/views/notice_update.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		NoticeDto noticeDto = new NoticeDto();
		noticeDto.setNotice_code(Integer.parseInt(request.getParameter("notice_code")));
		noticeDto.setNotice_title(request.getParameter("notice_title"));
		noticeDto.setNotice_content(request.getParameter("notice_content"));
		
		int result = noticeService.updateNotice(noticeDto);
		if(result == 1) { // 성공
			response.sendRedirect("notice-dtl?code=" + noticeDto.getNotice_code());
		} else { // 실패했을때는 수정 전 내용을 그대로 다시 돌려줘야 한다.
			NoticeDto preNoticeDto = noticeService.getNotice(request.getParameter("notice_code")); // 기존 db에 들어있는 내용
			preNoticeDto.setNotice_title(noticeDto.getNotice_title()); // title과 content만 수정하기 때문에 
			preNoticeDto.setNotice_content(noticeDto.getNotice_content()); // 그 내용들만 다시 set해준다.
			
			request.setAttribute("notice", preNoticeDto);
			request.getRequestDispatcher("/WEB-INF/views/notice_update.jsp").forward(request, response);
		}
	}

}
