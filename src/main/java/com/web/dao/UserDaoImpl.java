package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.web.db.DBConnectionMgr;

public class UserDaoImpl implements UserDao {
	
	private DBConnectionMgr pool = null;
	
	public UserDaoImpl() {  // 싱글톤
		pool = DBConnectionMgr.getInstance();
	}
	
	@Override
	public String getUserName(String id, String password) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String name = null;
		
		try { 
			con = pool.getConnection();  // 풀장에서 튜브 1개 대여
			sql = "select user_email from user_mst where user_id = ? and user_password = ?"; // 쿼리문 작성 ? 에는 입력한 데이터가 들어간다.
			pstmt = con.prepareStatement(sql);  // 쿼리문을 매개변수로 받는다.
			pstmt.setString(1, id);  // id  -> ?에 문자열 String을 set 해준다.
			pstmt.setString(2, password);  // password
			rs = pstmt.executeQuery(); // 쿼리를 실행하고 완성된 pstmt를 rs에 담는다.
			
			rs.next(); // 다음 커서 이동
			name = rs.getString(1); // 첫번째 컬럼의 결과를 가져온다. -> name에다가 담기
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // 전부 사용했기 때문에 전부 튜브 반납
		}
		
		return name;
	}

}
