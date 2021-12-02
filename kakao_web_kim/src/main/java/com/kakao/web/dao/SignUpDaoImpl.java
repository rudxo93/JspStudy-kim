package com.kakao.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kakao.web.db.DBConnectionMgr;
import com.kakao.web.dto.UserDto;

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

	@Override
	public int phoneNumberCheck(String phone, String name) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int flag = 0;
		
		try {
			con = pool.getConnection();
			sql = "select count(pm.phone_number), count(um.user_phone) from phonenumber_list_mst pm left outer join user_mst um on (um.user_phone = pm.phone_number) where pm.phone_number = ? and pm.phone_user_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			
			rs.next(); //  한 줄의 결과 
			flag = rs.getInt(1) + rs.getInt(2); // 결과에서 getint / db의 테이블 자료형을 따른다.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		
		return flag;
	}
	
	@Override
	public boolean signUp(UserDto userDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "insert into user_mst values(?, ?, ?, ?, now(), now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userDto.getUser_email());
			pstmt.setString(2, userDto.getUser_password());
			pstmt.setString(3, userDto.getUser_name());
			pstmt.setString(4, userDto.getUser_phone());
			
			pstmt.executeUpdate(); // 예외가 발생하지 않는다면 flag에는 true값이 들어간다. 문제가 생기면 false
			flag = true;
			return flag;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt); // ResultSet은 생성않했기 때문에 con과 pstmt만 반납
		}
		
		return flag;
	}

}
