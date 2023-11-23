package com.ssafy.pjt.board.model;

import lombok.Data;

@Data
public class BoardDto {
	private int articleNo;
	private int contentId;  // 추가함
	private String userId;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;
}
