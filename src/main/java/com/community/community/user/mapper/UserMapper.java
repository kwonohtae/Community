package com.community.community.user.mapper;

import com.community.community.user.dto.UserRequestDto;
import com.community.community.user.dto.UserResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserResponseDto> findAll(UserRequestDto userRequestDto); // findAll도 userRequestDto를 받도록 변경
    int countAll(UserRequestDto userRequestDto);
}
