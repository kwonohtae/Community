package com.community.community.board.service;

import java.util.List;

import com.community.community.board.dto.BoardDto;

public interface BoardService {
    List<BoardDto> getBoardList();
    BoardDto getBoardDetail(int boardSeq);
    void insertBoard(BoardDto boardDto);
}
