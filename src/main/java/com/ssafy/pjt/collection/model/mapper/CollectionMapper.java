package com.ssafy.pjt.collection.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pjt.collection.model.BookmarkDto;
import com.ssafy.pjt.collection.model.CollectionDto;

@Mapper
public interface CollectionMapper {

	// 찜목록 가져오기
	List<CollectionDto> listCollection(String userId) throws Exception;
	// 찜목록 생성하기
	void registCollection(CollectionDto dto) throws Exception;
	// 찜목록 보기
	CollectionDto detailCollectoin(int cId) throws Exception;
	// 찜목록 삭제
	void deleteCollection(int cId) throws Exception;
	// 찜목록 수정
	void updateCollection(CollectionDto dto) throws Exception;
	
	// 북마크 가져오기
	List<BookmarkDto> listBookmark(int cid) throws Exception;
	// 북마크 등록
	void registBookmark(BookmarkDto dto) throws Exception;
	// 북마크 삭제
	void deleteBookmark(int savedId) throws Exception;
	
	
}
