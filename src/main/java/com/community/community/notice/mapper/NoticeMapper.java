package com.community.community.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.community.community.notice.dto.NoticeRequestDto;
import com.community.community.notice.dto.NoticeResponseDto;

@Mapper
public interface NoticeMapper {
	List<NoticeResponseDto> findAll(NoticeRequestDto noticeRequestDto);
	int countAll(NoticeRequestDto noticeRequestDto);
	NoticeResponseDto findByNoticeId(Long noticeId);
	int save(NoticeRequestDto noticeRequestDto);
}
