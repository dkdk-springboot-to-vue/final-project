package com.ssafy.pjt.chat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.chat.model.ChatDto;
import com.ssafy.pjt.chat.model.ChatRoomDto;
import com.ssafy.pjt.chat.model.service.ChatService;
import com.ssafy.pjt.member.model.MemberDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/chat")
@Api("채팅 컨트롤러  API V1")
@Slf4j
public class ChatController {

	private ChatService service;

	public ChatController(ChatService service) {
		super();
		this.service = service;
	}
	
	@ApiOperation(value = "채팅방 생성", notes = "새로운 채팅방을 생성한다.")
	@PostMapping("/room")
	public ResponseEntity<?> register(
			@RequestBody @ApiParam(value = "채팅방 생성.", required = true) ChatRoomDto dto) {
		try {
			service.registRoom(dto);
			List<ChatRoomDto> list = service.listRoom();
			return new ResponseEntity<List<ChatRoomDto>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "채팅 조회", notes = "채팅방의 채팅을 조회한다(들어간다).")
	@GetMapping("/room")
	public ResponseEntity<?> listChat(
			@ApiParam(value = "채팅 정보.", required = true) String roomId) {
		try {
			List<ChatDto> list = service.listChat(roomId);
			return new ResponseEntity<List<ChatDto>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
