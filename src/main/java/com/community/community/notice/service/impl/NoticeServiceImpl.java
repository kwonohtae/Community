package com.community.community.notice.service.impl;

import com.community.community.notice.dto.NoticeRequestDto;
import com.community.community.notice.dto.NoticeResponseDto;
import com.community.community.notice.mapper.NoticeMapper;
import com.community.community.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;

    @Override
    public List<NoticeResponseDto> findAll(NoticeRequestDto noticeRequestDto) {
        return noticeMapper.findAll(noticeRequestDto);
    }

    @Override
    public int getTotalCount(NoticeRequestDto noticeRequestDto) {
        return noticeMapper.countAll(noticeRequestDto);
    }
    
    @Override
    public NoticeResponseDto findByNoticeId(Long noticeId) {
    	log.info("데이터 확인 :::: {}", noticeId);
    	return noticeMapper.findByNoticeId(noticeId);
    }
    
    @Override
    public int save(NoticeRequestDto noticeRequestDto) {
    noticeRequestDto.setWriter("user01");
    noticeMapper.save(noticeRequestDto);
    
    log.info("insert 후 값 확인 :::: {}", noticeRequestDto.getNoticeId());
    
    return noticeRequestDto.getNoticeId();
    }
    
}
