package com.community.community.attachments.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.community.attachments.dto.AttachmentsResponseDto;
import com.community.community.attachments.service.AttachmentsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/attachments")
public class AttachmentsController { // 클래스명 오타 수정: AttachmentsControlller -> AttachmentsController

    private final AttachmentsService attachmentsService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/download/{attachmentId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long attachmentId) {
        // 1. attachmentId로 파일 정보 조회
        AttachmentsResponseDto attachment = attachmentsService.getAttachmentById(attachmentId); // 단일 파일 조회 메소드 필요

        if (attachment == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            // 2. 파일 경로 생성 (파일 저장 경로 + DB에 저장된 파일 경로)
            Path filePath = Paths.get(uploadDir + File.separator + attachment.getFilePath()); // attachment.getFilePath()는 상대경로
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("File not found or not readable: " + attachment.getFileName());
            }

            // 3. Content-Disposition 헤더 설정 (다운로드 시 파일명)
            String encodedFileName = URLEncoder.encode(attachment.getFileName(), StandardCharsets.UTF_8.toString()).replace("+", "%20");
            String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(resource);

        } catch (MalformedURLException e) {
            log.error("File download path error: {}", attachment.getFilePath(), e);
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            log.error("File download IO error: {}", attachment.getFileName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
