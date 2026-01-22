package com.community.community.comments.dto;

import lombok.Data;

@Data
public class CommentRequestDto {
	
    private Long commentId;
    private Long boardId;
    private Long noticeId;
    private String userId;
    private String writer;
    private String comment;
    private String useYn;
    private String insertUser;
    private String updateUser;
}
