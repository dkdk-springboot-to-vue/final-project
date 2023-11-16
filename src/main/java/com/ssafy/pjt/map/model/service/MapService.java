package com.ssafy.pjt.map.model.service;

import java.util.List;

import com.ssafy.pjt.map.model.SidoGugunCodeDto;

public interface MapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	
}
