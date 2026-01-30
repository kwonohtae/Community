package com.community.community.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;
import com.community.community.board.service.BoardService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    

    @GetMapping("/list")
    public String boardList(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "insertDate", required = false) String sortField,
            @RequestParam(defaultValue = "desc", required = false) String sortOrder,
            Model model) {
    	BoardRequestDto boardRequestDto = new BoardRequestDto();
    	boardRequestDto.setPage(page);
        boardRequestDto.setStartDate(startDate);
        boardRequestDto.setEndDate(endDate);
        boardRequestDto.setKeyword(keyword);
        boardRequestDto.setSortField(sortField);
        boardRequestDto.setSortOrder(sortOrder);
        List<BoardResponseDto> boardList = boardService.findAll(boardRequestDto);
        int totalCount = boardService.getTotalCount(boardRequestDto);

        int totalPages = (int) Math.ceil((double) totalCount / boardRequestDto.getPageSize());

        log.info("boardList 진입 데이터 확인 ::::: {}" , boardRequestDto);
        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", boardRequestDto.getPageSize());
        model.addAttribute("startDate", startDate); // Add for Thymeleaf to retain search values
        model.addAttribute("endDate", endDate);     // Add for Thymeleaf to retain search values
        model.addAttribute("keyword", keyword);     // Add for Thymeleaf to retain search values
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortOrder", sortOrder);
        return "board/list";
    }

    @GetMapping("/writePage")
    public String boardWritePage(Model model) {
    	model.addAttribute("board", new BoardRequestDto());
        return "board/write";
    }

    @PostMapping("/save")
    public String saveBoard(@ModelAttribute BoardRequestDto boardRequestDto, @RequestParam(value = "attachments", required = false) List<MultipartFile> attachments, HttpSession session) { // HttpSession 추가
//        String userId = (String) session.getAttribute("userId"); // userId 가져오기
        String userId = "Test"; // userId 가져오기
//        if (userId == null) {
//            // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 에러 처리
//            return "redirect:/user/loginPage"; // 예시
//        }
        boardRequestDto.setUserId(userId); // userId 설정
        boardRequestDto.setWriter(userId); // writer 설정 (임시로 userId와 동일하게)

    	log.info("saveBoard 진입 데이터 확인1 ::::: {}", boardRequestDto);
    	// attachmentsService.saveFiles 호출은 BoardServiceImpl로 이동
    	int boardId = boardService.save(boardRequestDto, attachments); 
    	log.info("saveBoard 결과 데이터 확인 ::::: {}", boardId);
        return "redirect:/board/list?page=1";
    }

    // 답변 글 등록을 위한 API 엔드포인트 추가
    @PostMapping("/reply")
    public ResponseEntity<String> saveReply(@ModelAttribute BoardRequestDto boardRequestDto, @RequestParam(value = "attachments", required = false) List<MultipartFile> attachments, HttpSession session) {
//        String userId = (String) session.getAttribute("userId");
        String userId = "Test";
//        if (userId == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
//        }
        boardRequestDto.setUserId(userId);
        boardRequestDto.setWriter(userId); // 작성자도 userId로 설정 (임시)
        
        log.info("saveReply 진입 데이터 확인 ::::: {} ::::::  {} :::::: {} ", boardRequestDto , boardRequestDto.getParentId() , attachments);
        int replyId = boardService.save(boardRequestDto, attachments); 
        if (replyId > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("답변 글이 성공적으로 등록되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("답변 글 등록에 실패했습니다.");
        }
    }


    @GetMapping("/detail/{boardId}")
    public String boardDetail(@PathVariable Long boardId, @RequestParam(defaultValue = "1", required = false) int page, Model model) { // int -> Long 변경
    	log.info("boardDetail 진입 데이터 확인 ::: {} :::::: {} ", boardId , page);
    	boardService.updateView(boardId);
    	BoardResponseDto board = boardService.findByBoardId(boardId);
    	if(board != null) { // !board.equals("") 제거 (객체 비교에는 적합하지 않음)
    		model.addAttribute("board", board);
    		model.addAttribute("page", page);
    		
            BoardRequestDto replyBoard = new BoardRequestDto();
            replyBoard.setParentId(board.getBoardId());
            model.addAttribute("replyBoard", replyBoard);
    		
    		return "board/detail";	
    	}else {
    		return "error/500";
    	}
    }

    // 특정 게시글의 답변 (대댓글) 목록을 JSON으로 반환하는 API 추가
    @GetMapping("/replies/{parentId}")
    public ResponseEntity<List<BoardResponseDto>> getRepliesByParentId(@PathVariable Long parentId) {
        List<BoardResponseDto> replies = boardService.findRepliesByParentId(parentId);
        log.info("데이터 확인 :::::: {} ", replies);
        return ResponseEntity.ok(replies);
    }
}