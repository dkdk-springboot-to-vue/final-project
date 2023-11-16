package com.ssafy.pjt.collection.model;

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
@ApiModel(value = "CollectionDto : 컬렉션 정보", description = "사용자의 컬렉션 정보를 나타낸다.")
public class CollectionDto {

    @ApiModelProperty(value = "컬렉션 ID")
    private int cId;
    @ApiModelProperty(value = "사용자 ID")
    private String userId;
    @ApiModelProperty(value = "제목")
    private String title;
    @ApiModelProperty(value = "생성일")
    private String createdAt;

    // 생성자, getter, setter, toString 등은 필요에 따라 추가 가능합니다.
}
