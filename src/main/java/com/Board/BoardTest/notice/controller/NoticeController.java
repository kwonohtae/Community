package com.Board.BoardTest.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @GetMapping("/list")
    public String noticeList() {
        return "notice/list";
    }

    @GetMapping("/writePage")
    public String noticeWritePage() {
        return "notice/write";
    }

    @GetMapping("/{id}")
    public String noticeDetail(@PathVariable Long id, Model model) {
        // model.addAttribute("notice", noticeService.findById(id));
        return "notice/detail";
    }
}