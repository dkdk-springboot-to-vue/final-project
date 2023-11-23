package com.ssafy.pjt.board.model;

import java.util.List;

import lombok.Data;

@Data
public class BoardListDto {
	private List<BoardDto> articles;
	private int currentPage;
	private int totalPageCount;
}
