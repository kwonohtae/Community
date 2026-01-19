package com.community.community.notice.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoticeResponseDto {

	private int noticeId;
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
}
