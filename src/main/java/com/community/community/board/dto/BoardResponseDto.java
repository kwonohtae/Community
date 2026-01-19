package com.community.community.board.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BoardResponseDto {
    private int boardId;
    private String title;
    private String writer;
    private String detail;
    private int views;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
}
