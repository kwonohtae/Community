package com.community.community.user.mapper;

import com.community.community.user.dto.UserRequestDto;
import com.community.community.user.dto.UserResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserResponseDto> findAll(UserRequestDto userRequestDto);
    int countAll(UserRequestDto userRequestDto);
    int updateUseYn(String userId, String useYn);
    int updateUser(UserRequestDto userRequestDto);
    int addUser(UserRequestDto userRequestDto);
    UserResponseDto findById(String userId);
}
