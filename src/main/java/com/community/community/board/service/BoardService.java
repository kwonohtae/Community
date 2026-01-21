package com.community.community.board.service;

import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
    List<BoardResponseDto> findAll(BoardRequestDto boardRequestDto);
    BoardResponseDto findByBoardId(int boardId);
    int save(BoardRequestDto boardRequestDto, List<MultipartFile> files);
    int getTotalCount(BoardRequestDto boardRequestDto);
    int updateView(int boardId);
}
