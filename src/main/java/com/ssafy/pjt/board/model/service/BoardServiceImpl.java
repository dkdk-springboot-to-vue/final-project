package com.ssafy.pjt.board.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.pjt.board.model.BoardDto;
import com.ssafy.pjt.board.model.BoardListDto;
import com.ssafy.pjt.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService {

	@Override
	public void writeArticle(BoardDto boardDto) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public BoardListDto listArticle(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
