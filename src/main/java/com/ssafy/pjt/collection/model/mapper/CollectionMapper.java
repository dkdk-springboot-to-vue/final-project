package com.ssafy.pjt.collection.model.mapper;

import java.util.List;

import com.ssafy.pjt.collection.model.CollectionDto;

public interface CollectionMapper {

	//찜목록 가져오기
	List<CollectionDto> listCollection();
	
}
