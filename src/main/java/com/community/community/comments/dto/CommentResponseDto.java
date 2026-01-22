package com.community.community.comments.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    private Long commentId;
    private Long boardId;
    private Long noticeId;
    private String userId;
    private String writer;
    private String comment;
    private String useYn;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
}
