package com.community.community.board.mapper;

import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardResponseDto> findAll(BoardRequestDto boardRequestDto);
    BoardResponseDto findById(int id);
    void save(BoardRequestDto boardRequestDto);
}
