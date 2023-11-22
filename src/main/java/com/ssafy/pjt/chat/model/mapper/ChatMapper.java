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
	List<ChatRoomDto> listRoom(String userId) throws Exception;
	// 채팅방 상세목록 조회
	ChatRoomDto detailRoom(int roomId) throws Exception;
	// 채팅방 수정
	void updateRoom(ChatRoomDto dto) throws Exception;
	// 채팅방 삭제
	void deleteRoom(int roomId) throws Exception;
	
	// 최신 메시지 수정
	void updateRecentMessage(String message) throws Exception;
	
	// 채팅 조회
	List<ChatDto> listChat(int roomId) throws Exception;
	// 조회 여부
	void updateIsRead(int roomId) throws Exception;
	// 채팅 전송
	void sendChat(ChatDto dto) throws Exception;
}
