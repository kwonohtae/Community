package com.community.community.board.controller;

import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;
import com.community.community.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String boardList(Model model) {
        List<BoardResponseDto> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
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

    @GetMapping("/{id}")
    public String boardDetail(@PathVariable int id, Model model) {
        BoardResponseDto board = boardService.findById(id);
        model.addAttribute("board", board);
        return "board/detail";
    }
}
