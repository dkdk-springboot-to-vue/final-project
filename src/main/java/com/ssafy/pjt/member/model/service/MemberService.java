package com.ssafy.pjt.member.model.service;

import java.util.List;

import com.ssafy.pjt.member.model.MemberDto;

public interface MemberService {
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
	// 비밀번호 찾기 
	public String findPw(String id) throws Exception;
}
