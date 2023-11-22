package com.ssafy.pjt.collection.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.collection.model.BookmarkDto;
import com.ssafy.pjt.collection.model.CollectionDto;
import com.ssafy.pjt.collection.model.service.CollectionService;
import com.ssafy.pjt.map.controller.MapController;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/collection")
@Slf4j
public class CollectionController {

	private final CollectionService service;

	public CollectionController(CollectionService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("")
	public ResponseEntity<?> registCollection(@RequestBody CollectionDto dto) {
		log.info("registerCollection - 호출", dto);
		try {
			service.registCollection(dto);
			List<CollectionDto> list = service.listCollection(dto.getUserId());
			return new ResponseEntity<List<CollectionDto>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> listCollection(@PathVariable("userId") String userId) {
		log.info("listCollection - 호출", userId);
		try {
			List<CollectionDto> list =service.listCollection(userId);
			
			return new ResponseEntity<List<CollectionDto>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/detail/{cId}")
	public ResponseEntity<?> detailCollectoin(@PathVariable("cId") int cId) {
		log.info("detailCollectoin - 호출", cId);
		try {
			CollectionDto dto = service.detailCollectoin(cId);
			return new ResponseEntity<CollectionDto>(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/{cId}")
	public ResponseEntity<?> deleteCollection(@PathVariable("cId") int cId) {
		log.info("deleteCollection - 호출", cId);
		try {
			service.deleteCollection(cId);
			return new ResponseEntity<List<CollectionDto>>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/bookmark")
	public ResponseEntity<?> updateCollection(@RequestBody CollectionDto dto) {
		log.info("registerCollection - 호출", dto);
		try {
			service.updateCollection(dto);
			List<BookmarkDto> list = service.listBookmark(dto.getCId());
			return new ResponseEntity<List<BookmarkDto>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<?> listBookmark(@PathVariable("cid") int cid) {
		log.info("listBookmark - 호출", cid);
		try {
			List<BookmarkDto> list =service.listBookmark(cid);
			
			return new ResponseEntity<List<BookmarkDto>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/bookmark")
	public ResponseEntity<?> registBookmark(@RequestBody BookmarkDto dto) {
		log.info("registerCollection - 호출", dto);
		try {
			service.registBookmark(dto);
			List<BookmarkDto> list = service.listBookmark(dto.getCId());
			return new ResponseEntity<List<BookmarkDto>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/bookmark/{savedId}")
	public ResponseEntity<?> deleteBookmark(@PathVariable int savedId) {
		log.info("deleteBookmark - 호출", savedId);
		try {
			service.deleteBookmark(savedId);
			return new ResponseEntity<List<BookmarkDto>>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
