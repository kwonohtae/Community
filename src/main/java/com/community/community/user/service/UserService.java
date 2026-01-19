package com.community.community.user.service;

import com.community.community.user.dto.UserRequestDto;
import com.community.community.user.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> findAll(UserRequestDto userRequestDto);
    int getTotalCount(UserRequestDto userRequestDto);
}
