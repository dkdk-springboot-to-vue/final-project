package com.ssafy.pjt.map.model;

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
@ApiModel(value = "AttractionDto : 관광지 정보", description = "관광지 정보를 나타낸다")
public class AttractionDto {
	private int content_id;
	private int content_type_id;
	private String title;
	private String addr1;
	private String zipcode;
	private String tel;
	private String first_image;
	private String first_image2;
	private int readcount;
	private int sido_code;
	private int gugun_code;
	private double latitude;
	private double longitude;
	private String mlevel;
	
	private List<LikeDto> likes;
    @ApiModelProperty(value = "좋아요 개수")
    private int likeCount;
    @ApiModelProperty(value = "싫어요 개수")
    private int dislikeCount;
}
