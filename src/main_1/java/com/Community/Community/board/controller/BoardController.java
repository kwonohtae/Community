package com.community.community.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/list")
    public String boardList() {
        return "board/list";
    }

    @GetMapping("/writePage")
    public String boardWritePage() {
        return "board/write";
    }

    @GetMapping("/{id}")
    public String boardDetail(@PathVariable Long id, Model model) {
        // model.addAttribute("board", boardService.findById(id));
        return "board/detail";
    }
}
