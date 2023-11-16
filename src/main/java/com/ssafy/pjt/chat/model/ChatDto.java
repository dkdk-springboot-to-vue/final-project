package com.ssafy.pjt.chat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "ChatDto : 채팅 정보", description = "채팅 정보를 나타낸다.")
public class ChatDto {
	
    @ApiModelProperty(value = "채팅 ID")
    private int chatId;
    @ApiModelProperty(value = "채팅방 ID")
    private int roomId;
    @ApiModelProperty(value = "사용자 ID")
    private String userId;
    @ApiModelProperty(value = "내용")
    private String content;
    @ApiModelProperty(value = "생성일")
    private String createdAt;
    
    
    
}