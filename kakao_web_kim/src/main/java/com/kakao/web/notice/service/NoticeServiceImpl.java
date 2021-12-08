package com.kakao.web.notice.service;

import java.util.ArrayList;
import java.util.List;

import com.kakao.web.notice.model.dao.NoticeDao;
import com.kakao.web.notice.model.dao.NoticeDaoImpl;
import com.kakao.web.notice.model.dto.NoticeDto;

public class NoticeServiceImpl implements NoticeService {

	private NoticeDao noticeDao;
	private int noticeTotalCount;
	
	public NoticeServiceImpl() {
		noticeDao = new NoticeDaoImpl();
	}
	
	@Override
	public List<NoticeDto> getNoviceListAll() {
		List<NoticeDto> noticeListAll = noticeDao.getNoticeAll();
		noticeTotalCount = noticeListAll.size();
		
		return noticeListAll;
	}
	
	@Override
	public List<NoticeDto> getNoticeList(String pageNum) {
		List<NoticeDto> noticeListAll = getNoviceListAll(); // 전체 리스트를 받는다.
		List<NoticeDto> noticeList = new ArrayList<NoticeDto>(); // 비어있는 새로운 리스트 20개만 넣는다.
		int page = Integer.parseInt(pageNum);
		
		int startIndex = (page - 1) * 20; //1, 21, 41
		int endIndex = page * 20; //20, 40, 60
		
		for(int i = startIndex; i < endIndex && i < noticeTotalCount; i++) {
			noticeList.add(noticeListAll.get(i));
		}
		
		return noticeList;
	}
	
	@Override
	public int[] getNoticePages(String pageNum) {
		int page = Integer.parseInt(pageNum);
		
		int totalPage = noticeTotalCount % 20 == 0 ? noticeTotalCount / 20 : noticeTotalCount / 20 + 1;
		int startPage = page % 5 == 0? page - 4 : page - (page % 5) + 1;
		int endPage = startPage + 4 <= totalPage? startPage + 4 : totalPage;
		
		int[] pages = {totalPage, startPage, endPage};
		
		return pages;
	}
	
}
