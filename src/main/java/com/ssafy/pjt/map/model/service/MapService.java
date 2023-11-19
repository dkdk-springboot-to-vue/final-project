package com.ssafy.pjt.map.model.service;

import java.util.List;

import com.ssafy.pjt.map.model.AttractionDto;
import com.ssafy.pjt.map.model.LikeDto;
import com.ssafy.pjt.map.model.SidoGugunCodeDto;

public interface MapService {
	List<AttractionDto> getAttractionList(SidoGugunCodeDto dto) throws Exception;
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(int sido_code) throws Exception;
	
	// 좋아요 싫어요 조회
	public List<LikeDto> listLike(int contentId) throws Exception;

	void toggleLike(LikeDto dto) throws Exception;
	
}
