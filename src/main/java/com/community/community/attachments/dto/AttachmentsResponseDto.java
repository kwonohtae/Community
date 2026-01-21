package com.community.community.attachments.dto;

import lombok.Data;

@Data
public class AttachmentsResponseDto {

	private Long attachmentId;
	private Long noticeId;
	private Long boardId;
	private String fileName;
	private String filePath;
	private String fileSize;
                                                                           
}

