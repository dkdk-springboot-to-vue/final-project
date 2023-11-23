package com.ssafy.pjt.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pjt.board.model.BoardDto;



@Mapper
public interface BoardMapper {
	public void writeArticle(BoardDto boardDto) throws SQLException;
	public List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
	public int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	public BoardDto getArticle(int articleNo) throws SQLException;
	public void updateHit(int articleNo) throws SQLException;
	
	public void modifyArticle(BoardDto boardDto) throws SQLException;
	public void deleteArticle(int articleNo) throws SQLException;
}
