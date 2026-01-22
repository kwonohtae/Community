package com.community.community.board.service;

import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
    List<BoardResponseDto> findAll(BoardRequestDto boardRequestDto);
    BoardResponseDto findByBoardId(Long boardId); // int -> Long 변경
    int save(BoardRequestDto boardRequestDto, List<MultipartFile> files);
    int getTotalCount(BoardRequestDto boardRequestDto);
    int updateView(Long boardId); // int -> Long 변경
    List<BoardResponseDto> findRepliesByParentId(Long parentId); // 추가: 답변 글 조회 메서드
}