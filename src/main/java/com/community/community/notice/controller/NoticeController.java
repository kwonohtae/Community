package com.community.community.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.community.notice.dto.NoticeRequestDto;
import com.community.community.notice.dto.NoticeResponseDto;
import com.community.community.notice.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
    private final NoticeService noticeService;
	
    @GetMapping("/list")
    public String noticeList(Model model) {
    	
    	NoticeRequestDto noticeRequestDto = new NoticeRequestDto(); 
    	List<NoticeResponseDto> noticeList = noticeService.findAll(noticeRequestDto);
    	log.info("데이터 확인 ::::: {}" , noticeList.size());
    	model.addAttribute("noticeList", noticeList);
        return "notice/list";
    }

    @GetMapping("/writePage")
    public String noticeWritePage() {
        return "notice/write";
    }

    @GetMapping("/{noticeId}")
    public String noticeDetail(@PathVariable Long id, Model model) {
        // model.addAttribute("notice", noticeService.findById(id));
        return "notice/detail";
    }
}