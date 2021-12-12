package com.kakao.web.notice.service;

import java.util.List;

import com.kakao.web.notice.model.dto.NoticeDto;

public interface NoticeService {

	public List<NoticeDto> getNoviceListAll();
	public List<NoticeDto> getNoticeList(String pageNum);
	public int[] getNoticePages(String pageNum); // 게시글 페이징처리
	public int insertNotice(NoticeDto noticeDto); // 게시글 작성하기
	public NoticeDto getNotice(String code); // 게시글 가져오기
	public int updateNotice(NoticeDto noticeDto); // 게시글 수정하기
	public int deleteNotice(String code); // 게시글 삭제하기
 	
}
 