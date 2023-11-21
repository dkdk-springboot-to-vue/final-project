package com.ssafy.pjt.member.model.mapper;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.pjt.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	// 회원가입
	public void registMember(MemberDto dto) throws Exception;
	// 회원수정
	public void updateMember(MemberDto dto) throws Exception;
	// 모든 회원조회
	public List<MemberDto> listMember() throws Exception;
	// 회원 상세조회
	public MemberDto detailMember(String id) throws Exception;
	// 회원탈퇴
	public void deleteMember(String id) throws Exception;
	
	
	
	// 로그인
	MemberDto login(MemberDto memberDto) throws SQLException;
	MemberDto userInfo(String userId) throws SQLException;
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userid) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
	// 로그아웃 -> 컨트롤러에서 구현 
}
