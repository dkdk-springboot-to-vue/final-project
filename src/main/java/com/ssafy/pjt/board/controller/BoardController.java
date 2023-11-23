package com.ssafy.pjt.board.controller;


import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.board.model.BoardDto;
import com.ssafy.pjt.board.model.BoardListDto;
import com.ssafy.pjt.board.model.service.BoardService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/board")
public class BoardController {
	private BoardService boardService;

    public BoardController(BoardService boardService) {
    	super();
        this.boardService = boardService;
    }

//    @GetMapping("/list/{pgno}/{key}/{word}")
//    public Map<String, Object> getList(@PathVariable("pgno") String pgno, @PathVariable("key") String key, @PathVariable("word") String word ) throws Exception {
//        Map<String, String> map1 = new HashMap<>();
//        map1.put("pgno", pgno);
//        map1.put("key", key);
//        map1.put("word", word);
//
//        Map<String, Object> map = new HashMap<>();
//        List<BoardDto> list = boardService.listArticle(map1);
//        map.put("list", list);
//
//        return map;
//    }
    
    @GetMapping("/list")
    public ResponseEntity<?> getList(@RequestParam Map<String, String> map){
    	try {
    		System.out.println("conmap : "+map);
			BoardListDto boardListDto = boardService.listArticle(map);
			System.out.println("conli : "+boardListDto);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(boardListDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}
    }

    @PutMapping("/modify")
    public Map<String, Object> getModify(@RequestBody BoardDto boardDto) throws Exception {
        Map<String, Object> map = new HashMap<>();
        boardService.modifyArticle(boardDto);
        return map;
    }

    @GetMapping("/view/{articleNo}")
    public ResponseEntity<BoardDto> getView(@PathVariable("articleNo") String articleNo) throws NumberFormatException, Exception {
        BoardDto boardDto;
        boardService.updateHit(Integer.parseInt(articleNo));
        return new ResponseEntity<BoardDto>(boardService.getArticle(Integer.parseInt(articleNo)), HttpStatus.OK);
    }
//    @GetMapping("/view/{articleNo}")
//    public Map<String, Object> getView(@PathVariable("articleNo") String articleNo) throws Exception {
//    	Map<String, Object> map = new HashMap<>();
//    	BoardDto boardDto = boardService.getArticle(Integer.parseInt(articleNo));
//    	map.put("board", boardDto);
//    	return map;
//    }

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody BoardDto boardDto) {
        
        try {
			boardService.writeArticle(boardDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}
    }

    
    @DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("articleno") int articleno) throws Exception {
		boardService.deleteArticle(articleno);
		return ResponseEntity.ok().build();
	}

    //  아래는 ----- form으로 만 이동
//    @GetMapping("/write")
//    public String getWrite() {
//
//        return "/board/write";
//    }
//    @PostMapping("/modify")
//    public String Modify() throws Exception{
//        return "";
//    }
    
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
