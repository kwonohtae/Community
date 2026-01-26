package com.community.community.user.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String userId; // 검색 조건 등으로 활용될 수 있음
    private String password;
    private String userName;
    private String userNickName;
    private String userEmail;
    private String userPhone;
    private String role;
    private String useYn;

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
