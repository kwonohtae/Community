package com.community.community.board.service.impl;

import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;
import com.community.community.board.mapper.BoardMapper;
import com.community.community.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
	private final BoardMapper boardMapper;

    @Override
    public List<BoardResponseDto> findAll(BoardRequestDto boardRequestDto) {
        return boardMapper.findAll(boardRequestDto);
    }

    @Override
    public BoardResponseDto findById(int id) {
        return boardMapper.findById(id);
    }

    @Override
    public void save(BoardRequestDto boardRequestDto) {
        boardMapper.save(boardRequestDto);
    }

    @Override
    public int getTotalCount(BoardRequestDto boardRequestDto) {
        return boardMapper.countAll(boardRequestDto);
    }
}
