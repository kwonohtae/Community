package com.community.community.user.service.impl;

import com.community.community.user.dto.UserRequestDto;
import com.community.community.user.dto.UserResponseDto;
import com.community.community.user.mapper.UserMapper;
import com.community.community.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDto> findAll(UserRequestDto userRequestDto) {
        return userMapper.findAll(userRequestDto);
    }

    @Override
    public int getTotalCount(UserRequestDto userRequestDto) {
        return userMapper.countAll(userRequestDto);
    }

    @Override
    @Transactional
    public int deleteUserSoftly(String userId, String useYn) {
        return userMapper.updateUseYn(userId, useYn);
//        return 0;
    }

    @Override
    @Transactional
    public int updateUser(UserRequestDto userRequestDto) {
        return userMapper.updateUser(userRequestDto);
        // return 0;
    }

    @Override
    @Transactional
    public int addUser(UserRequestDto userRequestDto) {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return userMapper.addUser(userRequestDto);
    }

    @Override
    public UserResponseDto findById(String userId) {
        return userMapper.findById(userId);
    }
    
    @Override
    public UserResponseDto findByEmail(String userEmail) {
        return userMapper.findByEmail(userEmail);
    }
}
