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

	@Override
	@Transactional
	public void saveFiles(List<MultipartFile> attachments, String type, Long refId, String writer) {
		
        if (attachments.isEmpty()) {
            return;
        }

        String path = type + File.separator + LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
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
                if("notice".equals(type)) {
                	fileDto.setNoticeId(refId);
                } else if("board".equals(type)) {
                	fileDto.setBoardId(refId);
                }
                fileDto.setFileName(originalName);
                fileDto.setFilePath(path + File.separator + savedName);
                fileDto.setFileSize(fileSize);
                //  TODO: insertUser는 현재 세션 등에서 가져와야 함. 우선은 "system"으로 하드코딩.
//                fileDto.setInsertUser("system"); 
                fileDto.setInsertUser(writer); 
                
                attachmentsMapper.save(fileDto);

            } catch (IOException e) {
                log.error("file save error", e);
                // Consider how to handle partially successful uploads
                // 하나의 파일이라도 실패하면 전체 롤백을 위해 런타임 예외 발생
                throw new RuntimeException("File saving failed.", e);
            }
        }
	}
}
