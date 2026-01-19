package com.community.community.user.service.impl;

import com.community.community.user.dto.UserRequestDto;
import com.community.community.user.dto.UserResponseDto;
import com.community.community.user.mapper.UserMapper;
import com.community.community.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> findAll(UserRequestDto userRequestDto) {
        return userMapper.findAll(userRequestDto);
    }

    @Override
    public int getTotalCount(UserRequestDto userRequestDto) {
        return userMapper.countAll(userRequestDto);
    }
}
