package com.ssafy.pjt.map.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.pjt.map.model.AttrLikeMemberDto;
import com.ssafy.pjt.map.model.AttractionDto;
import com.ssafy.pjt.map.model.LikeDto;
import com.ssafy.pjt.map.model.SidoGugunCodeDto;
import com.ssafy.pjt.map.model.mapper.MapMapper;

@Service
public class MapServiceImpl implements MapService {

	private MapMapper mapper;
	
	public MapServiceImpl(MapMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		// TODO Auto-generated method stub
		return mapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(int sido_code) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getGugunInSido(sido_code);
	}

	@Override
	public List<AttractionDto> getAttractionList(SidoGugunCodeDto dto) throws Exception {
		// TODO Auto-generated method stub
		List<AttractionDto> attrlist = mapper.getAttractionList(dto);
	    for (AttractionDto attr : attrlist) {
	    	int likeCount = 0;
	    	int dislikeCount = 0;
	        List<LikeDto> likes = mapper.listLike(attr.getContent_id());
	        if(likes != null) {
	        	for(LikeDto like : likes) {
	        		if(like.getType() == 1) likeCount++;
	        		else dislikeCount++;
	        	}
	        }
	        attr.setLikes(likes);
	        attr.setLikeCount(likeCount);
	        attr.setDislikeCount(dislikeCount);
	    }
		return attrlist;
	}
	

	@Override
	public AttractionDto getAttractionDetail(int contentId) throws Exception {
		// TODO Auto-generated method stub
		AttractionDto dto = mapper.getAttractionDetail(contentId);
		List<LikeDto> likes = mapper.listLike(contentId);
		dto.setLikes(likes);
		int likeCount = 0;
		int dislikeCount = 0;
		for(LikeDto like: likes) {
    		if(like.getType() == 1) likeCount++;
    		else dislikeCount++;
		}
		dto.setLikeCount(likeCount);
		dto.setDislikeCount(dislikeCount);
		return dto;
	}


	@Override
	public List<LikeDto> listLike(int contentId) throws Exception {
		return mapper.listLike(contentId);
	}

	@Override
	public void toggleLike(LikeDto dto) throws Exception {
		// TODO Auto-generated method stub
		LikeDto existingLike = mapper.checkLike(dto);
		System.out.println(existingLike);
		if(existingLike == null) {
			// 아직 좋아요, 싫어요 안눌렀으면 등록
			mapper.registLike(dto);
		} else {
			if(existingLike.getType() == dto.getType()) {
				// 중복된 좋아요, 싫어요 눌렀으면 해제
				System.out.println("del : " +dto);
				mapper.deleteLike(dto);
			} else {
				int type = existingLike.getType();
				String lType = (type==1) ? "좋아요" : "싫어요";
				throw new Exception("이미 "+ lType +"를 선택했습니다");
			}
		}
	}

	// 좋아요 누른 유저목록
	@Override
	public List<AttrLikeMemberDto> listAttrLikeMember(int contentId) throws Exception {
		// TODO Auto-generated method stub
		return mapper.listAttrLikeMember(contentId);
	}

}
