package com.ssafy.pjt.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.pjt.member.model.MemberDto;
import com.ssafy.pjt.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberMapper mapper;
	
	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.mapper = memberMapper;
	}

	@Override
	public void registMember(MemberDto dto) throws Exception {
		mapper.registMember(dto);
	}

	@Override
	public void updateMember(MemberDto dto) throws Exception {
		mapper.updateMember(dto);
	}

	@Override
	public List<MemberDto> listMember() throws Exception {
		// TODO Auto-generated method stub
		return mapper.listMember();
	}
	
	@Override
	public MemberDto detailMember(String id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.detailMember(id);
	}

	@Override
	public void deleteMember(String id) throws Exception {
		mapper.deleteMember(id);

	}

	
	// login
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		return mapper.login(memberDto);
	}
	
	@Override
	public MemberDto userInfo(String userId) throws Exception {
		return mapper.userInfo(userId);
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		mapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return mapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		mapper.deleteRefreshToken(map);
	}

}
