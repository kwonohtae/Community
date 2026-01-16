package com.community.community.board.dto;

import lombok.Data;

@Data
public class BoardDto {
    
	private int boardId;
	private String title;
	private String writer;
	private String detail;
	private String views;
	private String useYn;
	private String insertUser;
	private String insertDate;
	private String updateUser;
	private String updateDate;
	
}
