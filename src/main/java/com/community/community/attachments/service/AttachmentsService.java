package com.community.community.attachments.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface AttachmentsService {
			
	/**
	 * 다중 파일 저장
	 * @param attachments
	 * @return
	 */
	void saveFiles(List<MultipartFile> attachments, String boardType, Long refId, String writer);
	
}
