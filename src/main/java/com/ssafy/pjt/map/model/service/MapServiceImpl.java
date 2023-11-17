package com.ssafy.pjt.map.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.pjt.map.model.AttractionDto;
import com.ssafy.pjt.map.model.LikeDto;
import com.ssafy.pjt.map.model.SidoGugunCodeDto;
import com.ssafy.pjt.map.model.mapper.MapMapper;

@Service
public class MapServiceImpl implements MapService {

	private MapMapper mapper;
	
	public MapServiceImpl(MapMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		// TODO Auto-generated method stub
		return mapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(int sido_code) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getGugunInSido(sido_code);
	}

	@Override
	public List<AttractionDto> getAttractionList(SidoGugunCodeDto dto) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getAttractionList(dto);
	}

	@Override
	public void registLike(LikeDto dto) throws Exception {
		mapper.registLike(dto);
	}

	@Override
	public void deleteLike(LikeDto dto) throws Exception {
		mapper.deleteLike(dto);
	}

	@Override
	public List<LikeDto> listLike(int contentId) throws Exception {
		return mapper.listLike(contentId);
	}

}
