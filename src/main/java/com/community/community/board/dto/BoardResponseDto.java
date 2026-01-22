package com.community.community.board.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.community.community.attachments.dto.AttachmentsResponseDto;
import lombok.Data;

@Data
public class BoardResponseDto {
	
    private Long boardId; // int -> Long 변경
    private Long parentId; // 추가
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
    private List<AttachmentsResponseDto> attachments;
}