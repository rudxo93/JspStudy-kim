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
		String sql = null;  // 쿼리문 담을 변수
		String name = null;  // 결과 전달
		
		try { 
			con = pool.getConnection();  // 풀장에서 튜브 1개 대여
			sql = "select user_email from user_mst where user_id = ? and user_password = ?"; // 쿼리문 작성 ? 에는 입력한 데이터가 들어간다.
			pstmt = con.prepareStatement(sql);  // 쿼리문을 매개변수로 받는다.
			pstmt.setString(1, id);  // id  -> spl의 ? 미완성 쿼리문에 set 한다
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
	
	@Override
	public int login(String id, String password) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int flag = 0;
		
		try {
			con = pool.getConnection();
			sql = "SELECT\r\n"
					+ "	COUNT(um.user_id),\r\n"
					+ "	COUNT(ud.user_password)\r\n"
					+ "FROM\r\n"
					+ "	user_mst um\r\n"
					+ "	LEFT OUTER JOIN user_mst ud ON (ud.user_id= um.user_id AND um.user_password = ?)\r\n"
					+ "WHERE\r\n"
					+ "	um.user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			rs.next();
			flag = rs.getInt(1) + rs.getInt(2); // 플래그를 더했을떄 0 이면 아이디 없음 / 1이면 비밀번호 불일치 / 2면 로그인성공
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // 튜브 반납
		}	
		return flag;
	}

}
