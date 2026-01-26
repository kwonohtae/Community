package com.community.community.notice.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.community.community.notice.dto.NoticeRequestDto;
import com.community.community.notice.dto.NoticeResponseDto;

public interface NoticeService {

	List<NoticeResponseDto> findAll(NoticeRequestDto noticeRequestDto);
	int getTotalCount(NoticeRequestDto noticeRequestDto);
	NoticeResponseDto findByNoticeId(Long noticeId);
	long save(NoticeRequestDto noticeRequestDto, List<MultipartFile> files);
	int updateView(Long noticeId);
}
