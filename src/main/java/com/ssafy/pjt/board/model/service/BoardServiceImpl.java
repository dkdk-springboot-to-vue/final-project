package com.ssafy.pjt.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.pjt.board.model.BoardDto;
import com.ssafy.pjt.board.model.BoardListDto;
import com.ssafy.pjt.board.model.mapper.BoardMapper;
import com.ssafy.pjt.util.PageNavigation;
import com.ssafy.pjt.util.SizeConstant;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardmapper;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardmapper = boardMapper;
	}

	@Override

	public void writeArticle(BoardDto boardDto) throws Exception {
		boardmapper.writeArticle(boardDto);
	}

	@Override
	public BoardListDto listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		System.out.println(map);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);
		
		
		String key = map.get("key");
//		if("userid".equals(key))
//			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;

		List<BoardDto> list = boardmapper.listArticle(param);
		int totalArticleCount = boardmapper.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;
		
		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setArticles(list);
		boardListDto.setCurrentPage(currentPage);
		boardListDto.setTotalPageCount(totalPageCount);
		return boardListDto;
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int totalCount = boardmapper.getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}


	@Override
	public void updateHit(int articleNo) throws Exception {
		boardmapper.updateHit(articleNo);
		
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		boardmapper.modifyArticle(boardDto);
		
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		boardmapper.deleteArticle(articleNo);
		
	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
