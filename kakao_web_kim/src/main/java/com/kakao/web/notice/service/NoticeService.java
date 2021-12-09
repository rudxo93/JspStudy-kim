package com.kakao.web.notice.service;

import java.util.List;

import com.kakao.web.notice.model.dto.NoticeDto;

public interface NoticeService {

	public List<NoticeDto> getNoviceListAll();
	public List<NoticeDto> getNoticeList(String pageNum);
	public int[] getNoticePages(String pageNum);
	public int insertNotice(NoticeDto noticeDto);
	
}
 