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
	public List<ChatRoomDto> listRoom() throws Exception {
		return mapper.listRoom();
	}

	@Override
	public List<ChatDto> listChat(String roomId) throws Exception {
		return mapper.listChat(roomId);
	}

	@Override
	public void sendChat(ChatDto dto) throws Exception {
		mapper.sendChat(dto);
	}

}
