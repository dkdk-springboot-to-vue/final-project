package com.ssafy.pjt.map.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getGugunInSido(sido);
	}

}
