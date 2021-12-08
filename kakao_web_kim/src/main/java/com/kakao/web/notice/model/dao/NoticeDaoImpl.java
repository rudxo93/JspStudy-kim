package com.kakao.web.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kakao.web.db.DBConnectionMgr;
import com.kakao.web.notice.model.dto.NoticeDto;

public class NoticeDaoImpl implements NoticeDao {
	
	private DBConnectionMgr pool;
	
	public NoticeDaoImpl() {
		pool = DBConnectionMgr.getInstance();
	}
	
	@Override
	public List<NoticeDto> getNoticeAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<NoticeDto> noticeAll = null;
		int index = 1;
		
		try {
			con = pool.getConnection();
			sql = "select * from notice_mst order by notice_code desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			noticeAll = new ArrayList<NoticeDto>();
			
			while(rs.next()) {
				NoticeDto notice = new NoticeDto();
				notice.setIndex(index);
				notice.setNotice_code(rs.getInt(1));
				notice.setNotice_title(rs.getString(2));
				notice.setNotice_writer(rs.getString(3));
				notice.setNotice_date(rs.getDate(4).toString());
				notice.setNotice_count(rs.getInt(5));
				index++;
				noticeAll.add(notice);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return noticeAll;
	}
	
	@Override
	public int insertNotice(NoticeDto noticeDto) { // insert를 두번 날린다. 한번은 user_mst, 한번은 notice_dtl
		
		return 0;
	}
}





