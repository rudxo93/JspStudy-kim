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
		Connection con = null;
		Connection con2 = null;
		Connection con3 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int result = 0;
		int maxNoticeCode = 0;
		
		try {
			con = pool.getConnection();
			con2 = pool.getConnection();
			con3 = pool.getConnection();
			
			sql = "select max(notice_code) from notice_mst";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			maxNoticeCode = rs.getInt(1) + 1; // 최댓값에 다음 키값으로 사용할거 +1
			
			sql = "insert into notice_mst values(?, ?, ?, now(), 0, now(), now())";
			pstmt2 = con2.prepareStatement(sql);
			pstmt2.setInt(1, maxNoticeCode);
			pstmt2.setString(2, noticeDto.getNotice_title());
			pstmt2.setString(3, noticeDto.getNotice_writer());
			result = pstmt2.executeUpdate();
			
			sql = "insert into notice_dtl values(?, ?, now(), now())";
			pstmt3 = con3.prepareStatement(sql);
			pstmt3.setInt(1, maxNoticeCode);
			pstmt3.setString(2, noticeDto.getNotice_content());
			result += pstmt3.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
			pool.freeConnection(con2, pstmt2);
			pool.freeConnection(con3, pstmt3);
		}
		return result;
	}
}





