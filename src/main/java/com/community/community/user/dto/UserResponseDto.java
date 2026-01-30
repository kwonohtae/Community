package com.community.community.user.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserResponseDto {
    private String userId;
    private String password;
    private String userName;
    private String userNickName;
    private String userPhone;
    private String userEmail;
    private String useYn;
    private String role;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
}
