package com.kakao.web.notice.model.dao;

import java.util.List;

import com.kakao.web.notice.model.dto.NoticeDto;
//
public interface NoticeDao {

	public List<NoticeDto> getNoticeAll();
	public int insertNotice(NoticeDto noticeDto);
	public NoticeDto getNotice(int notice_code);  // 게시물 가져오기
	
}
