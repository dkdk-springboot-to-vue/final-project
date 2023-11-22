package com.ssafy.pjt.chat.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.pjt.chat.model.ChatDto;
import com.ssafy.pjt.chat.model.ChatRoomDto;
import com.ssafy.pjt.chat.model.mapper.ChatMapper;

@Service
public class ChatServiceImpl implements ChatService {

	private ChatMapper mapper;
	
	public ChatServiceImpl(ChatMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public void registRoom(ChatRoomDto dto) throws Exception {
		mapper.registRoom(dto);
	}

	@Override
	public List<ChatRoomDto> listRoom(String userId) throws Exception {
		return mapper.listRoom(userId);
	}

	@Override
	public List<ChatDto> listChat(int roomId) throws Exception {
		return mapper.listChat(roomId);
	}

	@Override
	public void sendChat(ChatDto dto) throws Exception {
		System.out.println("service : "+dto );
		mapper.sendChat(dto);
	}

	@Override
	public ChatRoomDto detailRoom(int roomId) throws Exception {
		ChatRoomDto dto = mapper.detailRoom(roomId);
		return dto;
	}

}
