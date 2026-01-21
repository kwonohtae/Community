package com.community.community.notice.service.impl;

import com.community.community.notice.dto.NoticeRequestDto;
import com.community.community.notice.dto.NoticeResponseDto;
import com.community.community.notice.mapper.NoticeMapper;
import com.community.community.notice.service.NoticeService;
import com.community.community.attachments.service.AttachmentsService; // AttachmentsService import
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Transactional import
import org.springframework.web.multipart.MultipartFile; // MultipartFile import

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;
    private final AttachmentsService attachmentsService; // AttachmentsService 주입

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
    @Transactional
    public int save(NoticeRequestDto noticeRequestDto, List<MultipartFile> files) {
        noticeRequestDto.setWriter("user01"); // TODO: 실제 사용자 정보로 대체
        noticeMapper.save(noticeRequestDto);
        
        log.info("insert 후 값 확인 :::: {}", noticeRequestDto.getNoticeId());
        
        // 게시글 저장 후 생성된 noticeId를 가져와 첨부파일 서비스에 전달
        Long noticeId = (long) noticeRequestDto.getNoticeId();
        
        // 첨부파일이 있을 경우에만 saveFiles 호출
        if (files != null && !files.isEmpty()) {
            attachmentsService.saveFiles(files, "notice", noticeId, noticeRequestDto.getWriter());
        }
        
        return noticeRequestDto.getNoticeId();
    }
    
    @Override
    public int updateView(Long noticeId) {
    	return noticeMapper.updateView(noticeId); 
    }
    
}
