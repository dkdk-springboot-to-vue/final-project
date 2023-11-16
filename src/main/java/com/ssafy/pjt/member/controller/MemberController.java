package com.ssafy.pjt.member.controller;

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

import com.ssafy.pjt.member.model.MemberDto;
import com.ssafy.pjt.member.model.service.MemberService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/member")
public class MemberController {

	private MemberService service;

	public MemberController(MemberService service) {
		super();
		this.service = service;
	}
	
	@PostMapping(value = "")
	public ResponseEntity<?> register(@RequestBody MemberDto dto) {
		try {
			service.registMember(dto);
			List<MemberDto> list = service.listMember();
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value= "/{userId}")
	public ResponseEntity<?> detail(@PathVariable("userId") String userId){
		System.out.println(userId);
		try {
			MemberDto dto = service.detailMember(userId);
			return new ResponseEntity<MemberDto>(dto, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> delete(@PathVariable("userId") String userId){
		System.out.println(userId);
		try {
			service.deleteMember(userId);
			List<MemberDto> list = service.listMember();
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
		
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody MemberDto dto){
		try {
			service.updateMember(dto);
			List<MemberDto> list = service.listMember();
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
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
