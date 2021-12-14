package com.kakao.web.userInfo.model.dao;

import com.kakao.web.userInfo.model.dto.UpdateUserDto;

public interface UserInfoDao {
	public int updateUserInfo(UpdateUserDto updateUserDto);  // password 업데이트
	public int phoneNumberCheck(String phone, String name);  // phone 인증요청
}
