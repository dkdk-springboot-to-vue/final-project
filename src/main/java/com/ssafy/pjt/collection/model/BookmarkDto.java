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
@ApiModel(value = "BookmarkDto : 저장 정보", description = "사용자가 저장한 정보를 나타낸다.")
public class BookmarkDto {

    @ApiModelProperty(value = "북마크 ID")
    private int savedId;
    @ApiModelProperty(value = "컬렉션 ID")
    private int cId;
    @ApiModelProperty(value = "컨텐츠 ID")
    private int contentId;
    @ApiModelProperty(value = "사용자 ID")
    private String userId;
    @ApiModelProperty(value = "생성일")
    private String createdAt;
    
    @ApiModelProperty(value = "제목 (content title")
    private String title;
    private Double latitude;
    private Double longitude;
    private String first_image;
    private String addr1;
    
}
