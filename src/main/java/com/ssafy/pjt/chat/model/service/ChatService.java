package com.ssafy.pjt.chat.model.service;

import java.util.List;

import com.ssafy.pjt.chat.model.ChatDto;
import com.ssafy.pjt.chat.model.ChatRoomDto;

public interface ChatService {
	// 채팅방 등록
	void registRoom(ChatRoomDto dto) throws Exception;
	// 채팅방 목록 조회
	List<ChatRoomDto> listRoom(String userId) throws Exception;
	// 채팅방 상세목록 조회(입장)
	ChatRoomDto detailRoom(int roomId) throws Exception;
	
	// 채팅 조회
	List<ChatDto> listChat(int roomId) throws Exception;
	// 채팅 전송
	void sendChat(ChatDto dto) throws Exception;
}
