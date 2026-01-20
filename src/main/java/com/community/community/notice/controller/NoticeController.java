package com.community.community.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
    private final NoticeService noticeService;
	
    @GetMapping("/list")
    public String noticeList(@RequestParam(defaultValue = "1") int page, Model model) {
    	
    	NoticeRequestDto noticeRequestDto = new NoticeRequestDto();
    	noticeRequestDto.setPage(page);
    	List<NoticeResponseDto> noticeList = noticeService.findAll(noticeRequestDto);
    	int totalCount = noticeService.getTotalCount(noticeRequestDto);

        int totalPages = (int) Math.ceil((double) totalCount / noticeRequestDto.getPageSize());

    	log.info("데이터 확인 ::::: {}" , noticeList.size());
    	model.addAttribute("noticeList", noticeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", noticeRequestDto.getPageSize());
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