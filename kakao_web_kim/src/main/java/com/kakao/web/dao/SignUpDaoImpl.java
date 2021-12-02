package com.kakao.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kakao.web.db.DBConnectionMgr;

public class SignUpDaoImpl implements SignUpDao{
	 
	private DBConnectionMgr pool = null;
	
	public SignUpDaoImpl() {
		pool = DBConnectionMgr.getInstance();
	}

	@Override
	public int idCheck(String id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;  // db접근 기본생성
		String sql = null;  // sql 쿼리문 저장
		int flag = 2; // 리턴값 전달
		
		try {
			con = pool.getConnection();
			sql = "select count(user_email) from user_mst where user_email = ?";  // 미완성 쿼리
			pstmt = con.prepareStatement(sql); // sql문 담아주기
			pstmt.setString(1, id); // 첫번째 변수에 전달받은 매개변수 id를 넣어준다.
			rs = pstmt.executeQuery();  // 결과를 rs에 담아주기
			
			rs.next();
			flag = rs.getInt(1); // 첫번째 컬럼의 결과를 int로 가져온다.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); //  튜브 반납
		}
		
		return flag;
	}
	
	

}
