package com.ssafy.pjt.map.model;

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
@ApiModel(value = "LikeDto : 좋아요 정보", description = "좋아요 정보를 나타낸다.")
public class LikeDto {

    @ApiModelProperty(value = "컨텐츠 ID")
    private int contentId;
    @ApiModelProperty(value = "사용자 ID")
    private String userId;
    @ApiModelProperty(value = "좋아요 타입 (1:좋아요 / 2:싫어요)")
    private int type;
    @ApiModelProperty(value = "생성일")
    private String createdAt;
}
