package com.community.community.board.controller;

import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;
import com.community.community.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
    private final BoardService boardService;

    @GetMapping("/list")
    public String boardList(@RequestParam(defaultValue = "1") int page, Model model) {
    	BoardRequestDto boardRequestDto = new BoardRequestDto();
    	boardRequestDto.setPage(page);
        List<BoardResponseDto> boardList = boardService.findAll(boardRequestDto);
        int totalCount = boardService.getTotalCount(boardRequestDto);

        int totalPages = (int) Math.ceil((double) totalCount / boardRequestDto.getPageSize());

        log.info("데이터 확인 :::: {}" , boardList.size());
        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", boardRequestDto.getPageSize());
        return "board/list";
    }

    @GetMapping("/writePage")
    public String boardWritePage() {
        return "board/write";
    }

    @PostMapping("/save")
    public String saveBoard(@ModelAttribute BoardRequestDto boardRequestDto) {
        boardService.save(boardRequestDto);
        return "redirect:/board/list";
    }

    @GetMapping("/{boardId}")
    public String boardDetail(@PathVariable int boardId, Model model) {
        
    	log.info("진입 확인 ::: !! {}", boardId);
    	
    	BoardResponseDto board = boardService.findById(boardId);
        model.addAttribute("board", board);
        return "board/detail";
    }
}
