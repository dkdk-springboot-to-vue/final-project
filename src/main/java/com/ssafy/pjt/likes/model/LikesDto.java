package com.ssafy.pjt.likes.model;

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
@ApiModel(value = "LikesDto : 좋아요 정보", description = "좋아요 정보를 나타낸다.")
public class LikesDto {

    @ApiModelProperty(value = "좋아요 ID")
    private int likeId;
    @ApiModelProperty(value = "컨텐츠 ID")
    private int contentId;
    @ApiModelProperty(value = "사용자 ID")
    private String userId;
    @ApiModelProperty(value = "좋아요 타입")
    private int type;
    @ApiModelProperty(value = "생성일")
    private String createdAt;

    // 생성자, getter, setter, toString 등은 필요에 따라 추가 가능합니다.
}
