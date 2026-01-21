package com.community.community.board.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardRequestDto {
    
	private int boardId;
	private String title;
    private String writer;
    private String detail;
    private String startDate; // Added for search condition
    private String endDate;   // Added for search condition
    private String keyword;   // Added for search condition
    
    // Paging fields
    private int page = 1; // Current page number, default to 1
    private int pageSize = 10; // Items per page, default to 10
    private int offset; // Calculated offset for LIMIT clause

    public void setPage(int page) {
        this.page = page;
        this.offset = (page - 1) * this.pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.offset = (this.page - 1) * this.pageSize;
    }
}