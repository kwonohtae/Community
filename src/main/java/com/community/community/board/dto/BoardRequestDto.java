package com.community.community.board.dto;

import lombok.Data;

@Data
public class BoardRequestDto {
    private String title;
    private String writer;
    private String detail;
}
