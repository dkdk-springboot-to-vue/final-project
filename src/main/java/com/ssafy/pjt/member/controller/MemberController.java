package com.ssafy.pjt.member.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	
	
	// register
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
	
	
	// detail
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
	
	
	// delete
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
	
	
	// update
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
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody MemberDto dto, HttpServletRequest request){
//		System.out.println(userId+" "+userPass);
//		MemberDto dto = new MemberDto();
//		dto.setUserId(userId);
//		dto.setUserPass(userPass);
		System.out.println(dto);
		try {
			MemberDto loginDto = service.login(dto);
			System.out.println(loginDto);
			if(loginDto != null) {
				request.getSession().setAttribute("userInfo", loginDto.getUserId());
				return new ResponseEntity<MemberDto>(loginDto, HttpStatus.OK);
			} else {
				System.out.println("여기가 문제 !!!!");
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request){
		request.getSession().invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@GetMapping("/findpw/{userId}")
	public ResponseEntity<?> logout(@PathVariable("userId") String id, HttpServletRequest request) {
	    try {
	        String pw = service.findPw(id);

	        if (pw != null) {
	            return new ResponseEntity<String>(pw, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return exceptionHandling(e);
	    }
	} 
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
