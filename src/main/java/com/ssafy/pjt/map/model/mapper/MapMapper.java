package com.ssafy.pjt.map.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.pjt.map.model.AttractionDto;
import com.ssafy.pjt.map.model.SidoGugunCodeDto;

@Mapper
public interface MapMapper {
	List<AttractionDto> getAttractionList(@Param("gugun_code") int gugun_code, @Param("sido_code") int sido_code) throws SQLException;
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(int sido_code) throws SQLException;
	
}
