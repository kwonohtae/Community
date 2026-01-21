package com.community.community.attachments.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.community.community.attachments.dto.AttachmentsResponseDto;

public interface AttachmentsService {
			
	/**
	 * 다중 파일 저장
	 * @param attachments
	 * @return
	 */
	void saveFiles(List<MultipartFile> attachments, String boardType, Long refId, String writer); // writer 파라미터 다시 추가
	
	/**
	 * 첨부파일 조회
	 * @param refId
	 * @param boardType
	 * @return
	 */
	List<AttachmentsResponseDto> getAttachments(Long refId, String boardType);

	/**
	 * 첨부파일 단건 조회
	 * @param attachmentId
	 * @return
	 */
	AttachmentsResponseDto getAttachmentById(Long attachmentId);

}
