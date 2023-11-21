package com.ssafy.pjt.map.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.map.model.AttrLikeMemberDto;
import com.ssafy.pjt.map.model.AttractionDto;
import com.ssafy.pjt.map.model.LikeDto;
import com.ssafy.pjt.map.model.SidoGugunCodeDto;
import com.ssafy.pjt.map.model.service.MapService;
import com.ssafy.pjt.member.model.MemberDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/map")
@CrossOrigin("*")
@Slf4j
public class MapController {

	private MapService service;

	public MapController(MapService service) {
		super();
		this.service = service;
	}
	
	@ApiOperation(value = "시도 정보 조회", notes = "시도 코드와 이름 조회")
	@GetMapping("")
	public ResponseEntity<?> sido(){
		log.info("sido - 호출");
		List<SidoGugunCodeDto> sidoList;
		try {
			sidoList = service.getSido();
			return new ResponseEntity<List<SidoGugunCodeDto>>(sidoList, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}
		
	}
	
	@ApiOperation(value = "구군 정보 조회", notes = "시도 코드 내에 있는 구군 코드와 이름 조회")
	@GetMapping("/gugun/{sido_code}")
	public ResponseEntity<?> gugun(@PathVariable("sido_code") @ApiParam(value = "조회시 필요한 시도 코드정보", required = true) int sido_code){
		log.info("gugun - 호출");
		List<SidoGugunCodeDto> gugunList;
		try {
			gugunList = service.getGugunInSido(sido_code);
			return new ResponseEntity<List<SidoGugunCodeDto>>(gugunList, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}

	}
	
	@ApiOperation(value = "관광지 정보 조회", notes = "시도, 구군 코드에 존재하는 관광지 조회")
	@PostMapping("/mapview")
	public ResponseEntity<?> getAttrList(@RequestBody @ApiParam(value = "조회할 시도, 구군 정보", required = true) SidoGugunCodeDto dto) {
		log.info("getAttrList - 호출 - {}", dto);
		List<AttractionDto> attractionList;
		try {
			attractionList = service.getAttractionList(dto);
			return new ResponseEntity<List<AttractionDto>>(attractionList, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}

	}
	
	@ApiOperation(value = "관광지 상세정보 조회", notes = "광광지 상새정보 조회")
	@GetMapping("/attr/{contentId}")
	public ResponseEntity<?> getAttrDetail(@PathVariable("contentId") @ApiParam(value = "조회할 정보 아이디", required = true) int contentId) {
		log.info("getAttrList - 호출 - {}", contentId);
		try {
			AttractionDto dto = service.getAttractionDetail(contentId);
			return new ResponseEntity<AttractionDto>(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}

	}
	
	@ApiOperation(value = "좋아요 등록, 취소", notes = "좋아요, 싫어요를 등록/취소하고 서로 다른 감정표현이 동시에 등록되지 않게")
	@PostMapping(value = "like")
	public ResponseEntity<?> toggleLike(@RequestBody LikeDto dto) {
		log.info("toggleLike - 호출 - {}", dto);
		try {
			service.toggleLike(dto);
			List<LikeDto> list = service.listLike(dto.getContentId());
			return new ResponseEntity<List<LikeDto>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "관광지 상세정보 조회", notes = "광광지 상새정보 조회")
	@GetMapping("/attr/like/{contentId}")
	public ResponseEntity<?> listAttrLikeMember(@PathVariable("contentId") @ApiParam(value = "조회할 관광지 아이디", required = true) int contentId) {
		log.info("listAttrLikeMember - 호출 - {}", contentId);
		
		try {
			List<AttrLikeMemberDto> list = service.listAttrLikeMember(contentId);
			return new ResponseEntity<List<AttrLikeMemberDto>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}

	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
