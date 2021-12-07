package com.kakao.web.notice.model.dto;

public class Notice {
	
	private int index;
	private int notice_num;
	private String notice_title;
	private String notice_writer;
	private String notice_date;
	private int notice_count;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_writer() {
		return notice_writer;
	}
	public void setNotice_writer(String notice_writer) {
		this.notice_writer = notice_writer;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public int getNotice_count() {
		return notice_count;
	}
	public void setNotice_count(int notice_count) {
		this.notice_count = notice_count;
	}
	
	@Override
	public String toString() {
		return "Notice [index=" + index + ", notice_num=" + notice_num + ", notice_title=" + notice_title
				+ ", notice_writer=" + notice_writer + ", notice_date=" + notice_date + ", notice_count=" + notice_count
				+ "]";
	}
	
	
	
}
