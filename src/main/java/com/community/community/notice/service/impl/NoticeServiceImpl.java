package com.community.community.notice.service.impl;

import com.community.community.notice.dto.NoticeRequestDto;
import com.community.community.notice.dto.NoticeResponseDto;
import com.community.community.notice.mapper.NoticeMapper;
import com.community.community.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
