package com.ssafy.pjt.member.model.mapper;
import java.util.List;

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
	public MemberDto login(MemberDto dto) throws Exception;
	// 로그아웃 -> 컨트롤러에서 구현 
}
