package com.community.community.board.service;

import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    List<BoardResponseDto> findAll();
    BoardResponseDto findById(int id);
    void save(BoardRequestDto boardRequestDto);
}
