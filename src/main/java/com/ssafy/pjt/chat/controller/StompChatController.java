package com.ssafy.pjt.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ssafy.pjt.chat.model.ChatDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StompChatController {

	private final SimpMessageSendingOperations  sendingOperations; // 특정 Broker로 메시지 전달
	
	//stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
	// /app/chat/enter
//	@MessageMapping("/chat/enter")
//	public void enter(ChatDto chatDto) {
//		chatDto.setContent(chatDto.getUserId() + "님이 채팅방에 참여햇슴");
//		template.convertAndSend("/sub/chat/room" + chatDto.getRoomId(), chatDto);
//	}
	
    @MessageMapping(value = "/chat/message")
    public void message(ChatDto chatDto){
    	sendingOperations.convertAndSend("/sub/chat/room/" + chatDto.getRoomId(), chatDto);
    }
	
}
