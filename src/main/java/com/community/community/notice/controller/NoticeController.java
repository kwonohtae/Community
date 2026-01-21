package com.community.community.notice.controller;

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
    public String noticeList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String keyword,
            Model model) {
    	
    	NoticeRequestDto noticeRequestDto = new NoticeRequestDto();
    	noticeRequestDto.setPage(page);
        noticeRequestDto.setStartDate(startDate);
        noticeRequestDto.setEndDate(endDate);
        noticeRequestDto.setKeyword(keyword);
    	List<NoticeResponseDto> noticeList = noticeService.findAll(noticeRequestDto);
    	int totalCount = noticeService.getTotalCount(noticeRequestDto);

        int totalPages = (int) Math.ceil((double) totalCount / noticeRequestDto.getPageSize());

    	log.info("noticeList 진입 데이터 확인 ::::: {}" , noticeList.size());
    	model.addAttribute("noticeList", noticeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", noticeRequestDto.getPageSize());
        model.addAttribute("startDate", startDate); // Add for Thymeleaf to retain search values
        model.addAttribute("endDate", endDate);     // Add for Thymeleaf to retain search values
        model.addAttribute("keyword", keyword);     // Add for Thymeleaf to retain search values
        return "notice/list";
    }

    @GetMapping("/writePage")
    public String noticeWritePage() {
        return "notice/write";
    }
    
    @Transactional
    @PostMapping("/save")
    public String saveNotice(@ModelAttribute NoticeRequestDto noticeRequestDto, @RequestParam(value = "attachments", required = false) List<MultipartFile> attachments) {
        
    	log.info("saveNotice 진입 데이터 확인1 ::::: {}",noticeRequestDto);
    	if(!attachments.get(0).isEmpty()) {
    		log.info("saveNotice 진입 데이터 확인2 ::::: {}",attachments.get(0).getOriginalFilename());	
    	}
    	
    	int noticeId = 0;
    	noticeId = noticeService.save(noticeRequestDto);
    	
    	log.info("saveNotice 결과 데이터 확인 ::::: {}", noticeId);
        return "redirect:/notice/list";
    }

    @GetMapping("/detail/{noticeId}")
    public String noticeDetail(@PathVariable Long noticeId, @RequestParam int page, Model model) {
    	log.info("noticeDetail 진입 데이터 확인 ::::  {}  ", noticeId);
    	NoticeResponseDto notice = new NoticeResponseDto();
    	notice = noticeService.findByNoticeId(noticeId);
    	if(notice != null || !notice.equals("")) {
    		model.addAttribute("notice", notice);
    		model.addAttribute("page", page);
    		return "notice/detail";
    	}else {
    		return "error/500";
    	}       
    }
}