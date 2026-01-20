package com.community.community.notice.dto;

import lombok.Data;

@Data
public class NoticeRequestDto {

	private String title;
	private String detail;
	private String writer;

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
