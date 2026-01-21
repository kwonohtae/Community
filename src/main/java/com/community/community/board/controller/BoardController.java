package com.community.community.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String keyword,
            Model model) {
    	BoardRequestDto boardRequestDto = new BoardRequestDto();
    	boardRequestDto.setPage(page);
        boardRequestDto.setStartDate(startDate);
        boardRequestDto.setEndDate(endDate);
        boardRequestDto.setKeyword(keyword);
        List<BoardResponseDto> boardList = boardService.findAll(boardRequestDto);
        int totalCount = boardService.getTotalCount(boardRequestDto);

        int totalPages = (int) Math.ceil((double) totalCount / boardRequestDto.getPageSize());

        log.info("boardList 진입 데이터 확인 ::::: {}" , boardList.size());
        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", boardRequestDto.getPageSize());
        model.addAttribute("startDate", startDate); // Add for Thymeleaf to retain search values
        model.addAttribute("endDate", endDate);     // Add for Thymeleaf to retain search values
        model.addAttribute("keyword", keyword);     // Add for Thymeleaf to retain search values
        return "board/list";
    }

    @GetMapping("/writePage")
    public String boardWritePage() {
        return "board/write";
    }

    @Transactional
    @PostMapping("/save")
    public String saveBoard(@ModelAttribute BoardRequestDto boardRequestDto, @RequestParam(value = "attachments", required = false) List<MultipartFile> attachments) {
        // boardService.save(boardRequestDto);
    	log.info("saveBoard 진입 데이터 확인1 ::::: {}", boardRequestDto);
    	log.info("saveBoard 진입 데이터 확인2 ::::: {}", attachments);
        return "redirect:/board/list";
    }

    @GetMapping("/detail/{boardId}")
    public String boardDetail(@PathVariable int boardId, @RequestParam int page, Model model) {
    	log.info("boardDetail 진입 데이터 확인 ::: {} :::::: {} ", boardId , page);
    	
    	BoardResponseDto board = boardService.findByBoardId(boardId);
    	if(board != null || !board.equals("")) {
    		model.addAttribute("board", board);
    		model.addAttribute("page", page);
    		return "board/detail";	
    	}else {
    		return "error/500";
    	}
        
        
    }
}
