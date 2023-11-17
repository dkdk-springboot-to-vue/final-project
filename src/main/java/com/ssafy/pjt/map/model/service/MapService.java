package com.ssafy.pjt.map.model.service;

import java.util.List;

import com.ssafy.pjt.map.model.AttractionDto;
import com.ssafy.pjt.map.model.SidoGugunCodeDto;

public interface MapService {
	List<AttractionDto> getAttractionList(int gugun_code, int sido_code) throws Exception;
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(int sido_code) throws Exception;
	
}
