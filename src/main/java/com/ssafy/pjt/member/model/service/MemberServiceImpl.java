package com.ssafy.pjt.member.model.service;

import java.util.List;

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

	@Override
	public MemberDto login(MemberDto dto) throws Exception {
		// TODO Auto-generated method stub
		return mapper.login(dto);
	}


}
