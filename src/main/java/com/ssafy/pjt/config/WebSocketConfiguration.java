//package com.ssafy.pjt.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@EnableWebSocket
//@RequiredArgsConstructor
//public class WebSocketConfiguration implements WebSocketConfigurer {
//
//	private final WebSocketHandler webSocketHandler;
//	
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addHandler(webSocketHandler, "/ws-chat").setAllowedOrigins("*");
//	}
//
//}
