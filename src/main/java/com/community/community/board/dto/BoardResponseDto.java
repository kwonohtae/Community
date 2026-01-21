package com.community.community.board.dto;

import java.time.LocalDateTime;
import java.util.List; // Add this import
import com.community.community.attachments.dto.AttachmentsResponseDto; // Add this import
import lombok.Data;

@Data
public class BoardResponseDto {
	
	
    private int boardId;
    private int communityId;
    private String userId;
    private String title;
    private String writer;
    private String detail;
    private int views;
    private String useYn;
    private String insertUser;
    private LocalDateTime insertDate;
    private String updateUser;
    private LocalDateTime updateDate;
    private List<AttachmentsResponseDto> attachments; // Add this field

}
