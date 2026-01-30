package com.community.community.user.service;

import com.community.community.user.dto.UserRequestDto;
import com.community.community.user.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> findAll(UserRequestDto userRequestDto);
    int getTotalCount(UserRequestDto userRequestDto);
    int deleteUserSoftly(String userId, String useYn);
    int updateUser(UserRequestDto userRequestDto);
    int addUser(UserRequestDto userRequestDto);
    UserResponseDto findById(String userId);
    UserResponseDto findByEmail(String userEmail);
}
