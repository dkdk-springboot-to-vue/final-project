package com.ssafy.pjt.chat.model;

import java.util.List;

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
@ApiModel(value = "ChatroomDto : 채팅방 정보", description = "채팅방 정보를 나타낸다.")
public class ChatRoomDto {

    @ApiModelProperty(value = "채팅방 ID")
    private int roomId;
    @ApiModelProperty(value = "송신자 ID")
    private String senderId;
    @ApiModelProperty(value = "수신자 ID")
    private String receiverId;
    @ApiModelProperty(value = "생성일")
    private String createdAt;
    
    @ApiModelProperty(value = "채팅목록")
    private List<ChatDto> chatList;
}
