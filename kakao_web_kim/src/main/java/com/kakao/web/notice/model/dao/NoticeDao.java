package com.kakao.web.notice.model.dao;

import java.util.List;

import com.kakao.web.notice.model.dto.Notice;

public interface NoticeDao {

	public List<Notice> getNoticeAll();
	
}
