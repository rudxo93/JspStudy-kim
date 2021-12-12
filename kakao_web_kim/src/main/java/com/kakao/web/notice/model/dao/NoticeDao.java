package com.kakao.web.notice.model.dao;

import java.util.List;

import com.kakao.web.notice.model.dto.NoticeDto;
//
public interface NoticeDao {

	public List<NoticeDto> getNoticeAll(); // 모든 게시글 조회하기
	public int insertNotice(NoticeDto noticeDto); // 게시글 작성하기
	public NoticeDto getNotice(int notice_code);  // 게시글 가져오기
	public int updateNotice(NoticeDto noticeDto); // 게시글 수정하기
	public int deleteNotice(int notice_code);  // 게시글 삭제하기
	
}
