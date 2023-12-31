package com.ssafy.pjt.map.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.pjt.map.model.AttrLikeMemberDto;
import com.ssafy.pjt.map.model.AttractionDto;
import com.ssafy.pjt.map.model.LikeDto;
import com.ssafy.pjt.map.model.SidoGugunCodeDto;

@Mapper
public interface MapMapper {
	List<AttractionDto> getAttractionList(SidoGugunCodeDto dto) throws SQLException;
	List<AttractionDto> getAttractionListByDist(AttractionDto dto) throws SQLException;
	AttractionDto getAttractionDetail(int contentId) throws SQLException;
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(int sido_code) throws SQLException;
	
	
	// 좋아요, 싫어요 등록
	void registLike(LikeDto dto) throws Exception;
	// 좋아요 싫어요 삭제
	void deleteLike(LikeDto dto) throws Exception;
	// 좋아요 조회
	List<LikeDto> listLike(int contentId) throws Exception;
	// 좋아요 중복체크
	LikeDto checkLike(LikeDto dto) throws Exception;
	
	// 좋아요 누른 유저목록
	List<AttrLikeMemberDto> listAttrLikeMember(int contentId) throws Exception;
}
