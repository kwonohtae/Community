package com.community.community.board.mapper;

import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardResponseDto> findAll(BoardRequestDto boardRequestDto);
    BoardResponseDto findByBoardId(Long boardId); // int -> Long 변경
    int save(BoardRequestDto boardRequestDto);
    int countAll(BoardRequestDto boardRequestDto);
    int updateView(Long boardId); // int -> Long 변경
    List<BoardResponseDto> findRepliesByParentId(Long parentId);
}