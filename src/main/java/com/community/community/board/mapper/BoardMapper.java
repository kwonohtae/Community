package com.community.community.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.community.community.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
    List<BoardDto> getBoardList();
    BoardDto getBoardDetail(int boardSeq);
    void insertBoard(BoardDto boardDto);
}
