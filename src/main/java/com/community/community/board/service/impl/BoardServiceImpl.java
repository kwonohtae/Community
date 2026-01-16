package com.community.community.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.community.board.dto.BoardDto;
import com.community.community.board.mapper.BoardMapper;
import com.community.community.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardDto> getBoardList() {
        return boardMapper.getBoardList();
    }

    @Override
    public BoardDto getBoardDetail(int boardSeq) {
        return boardMapper.getBoardDetail(boardSeq);
    }

    @Override
    public void insertBoard(BoardDto boardDto) {
        boardMapper.insertBoard(boardDto);
    }
}
