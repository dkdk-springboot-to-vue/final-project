package com.ssafy.pjt.map.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "AttrLikeMemberDto : 관광지에 좋아요 누른 유저정보", description = "관광지에 좋아요 누른 유저정보 나타냄")
public class AttrLikeMemberDto {
	private String title;
	private int likeContentId;
	private String likeUserId;
	private String likeUserName;
	private int type;
}
