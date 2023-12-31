package com.ssafy.pjt.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ssafy.pjt.chat.model.ChatDto;
import com.ssafy.pjt.chat.model.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StompChatController {

	private final ChatService service;

	private final SimpMessageSendingOperations  sendingOperations; // 특정 Broker로 메시지 전달
	private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
	
	//stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
	// /app/chat/enter
//	@MessageMapping("/chat/enter")
//	public void enter(ChatDto chatDto) {
//		chatDto.setContent(chatDto.getUserId() + "님이 채팅방에 참여햇슴");
//		template.convertAndSend("/sub/chat/room" + chatDto.getRoomId(), chatDto);
//	}
	
    @MessageMapping(value = "/chat")
    public void  message(ChatDto chatDto){
    	System.out.println(chatDto);
    	template.convertAndSend("/sub/chat/" + chatDto.getRoomId(), chatDto);
    	try {
			service.sendChat(chatDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
}
