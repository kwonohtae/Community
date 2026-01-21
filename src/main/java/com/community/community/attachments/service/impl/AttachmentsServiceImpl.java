package com.community.community.attachments.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.community.community.attachments.dto.AttachmentsRequestDto;
import com.community.community.attachments.dto.AttachmentsResponseDto; // 단일 import 유지
import com.community.community.attachments.mapper.AttachmentsMapper;
import com.community.community.attachments.service.AttachmentsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentsServiceImpl implements AttachmentsService{
	
    private final AttachmentsMapper attachmentsMapper;
	
    @Value("${file.upload-dir}")
    private String uploadPath;

	// saveAttachment 메소드 제거됨
	
	@Override
	@Transactional
	public void saveFiles(List<MultipartFile> attachments, String boardType, Long refId, String writer) { // writer 파라미터 추가
		
        if (attachments.isEmpty()) {
            return;
        }

        String path = boardType + File.separator + LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        File dir = new File(uploadPath + File.separator + path);
        
        if (dir.exists() == false) {
        	dir.mkdirs();
        }

        for (MultipartFile attachment : attachments) {
            if (attachment.isEmpty()) {
                continue;
            }

            try {
                String originalName = attachment.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String extension = "";
                if (originalName.contains(".")) {
                	extension = originalName.substring(originalName.lastIndexOf("."));
                }
                String savedName = uuid + extension;
                long fileSize = attachment.getSize();

                File target = new File(dir, savedName);
                attachment.transferTo(target);

                AttachmentsRequestDto fileDto = new AttachmentsRequestDto();
                if("notice".equals(boardType)) {
                	fileDto.setNoticeId(refId);
                } else if("board".equals(boardType)) {
                	fileDto.setBoardId(refId);
                }
                fileDto.setFileName(originalName);
                fileDto.setFilePath(path + File.separator + savedName);
                fileDto.setFileSize(fileSize);
                fileDto.setInsertUser(writer); // writer 파라미터 사용
                
                attachmentsMapper.save(fileDto);

            } catch (IOException e) {
                log.error("file save error", e);
                throw new RuntimeException("File saving failed.", e);
            }
        }
	}

	@Override
	public List<AttachmentsResponseDto> getAttachments(Long refId, String boardType) {
		return attachmentsMapper.findByRefIdAndType(refId, boardType);
	}
	
	@Override
	public AttachmentsResponseDto getAttachmentById(Long attachmentId) {
		return attachmentsMapper.findById(attachmentId);
	}
}
