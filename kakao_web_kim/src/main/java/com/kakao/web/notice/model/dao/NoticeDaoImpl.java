package com.kakao.web.notice.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.kakao.web.db.DBConnectionMgr;
import com.kakao.web.notice.model.dto.Notice;

public class NoticeDaoImpl implements NoticeDao {
	
	private DBConnectionMgr pool;
	
	public NoticeDaoImpl() {
		pool = DBConnectionMgr.getInstance();
	}
	
	@Override
	public List<Notice> getNoticeAll() {
		List<Notice> noticeAll;
		
		noticeAll = new ArrayList<Notice>();
		
		return noticeAll;
	}

}
