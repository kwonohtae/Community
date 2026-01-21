package com.community.community.attachments.dto;

import lombok.Data;

@Data
public class AttachmentsRequestDto {
	
	private Long noticeId;
	private Long boardId;
	private String fileName;
	private String filePath;
	private long fileSize;
	private String useYn = "Y";
	private String insertUser;
	
}
