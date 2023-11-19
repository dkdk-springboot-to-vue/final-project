package com.ssafy.pjt.chat.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pjt.chat.model.ChatDto;
import com.ssafy.pjt.chat.model.ChatRoomDto;

@Mapper
public interface ChatMapper {
	// 채팅방 등록
	void registRoom(ChatRoomDto dto) throws Exception;
	// 채팅방 목록 조회
	List<ChatRoomDto> listRoom() throws Exception;
	// 채팅방 상세목록 조회
	ChatRoomDto detailRoom(String roomId) throws Exception;
	
	// 채팅 조회
	List<ChatDto> listChat(String roomId) throws Exception;
	// 채팅 전송
	void sendChat(ChatDto dto) throws Exception;
}
