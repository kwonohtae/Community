package com.community.community.board.service.impl;

import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;
import com.community.community.board.mapper.BoardMapper;
import com.community.community.board.service.BoardService;
import com.community.community.attachments.service.AttachmentsService; // AttachmentsService import
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Transactional import
import org.springframework.web.multipart.MultipartFile; // MultipartFile import

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
    private final AttachmentsService attachmentsService; // AttachmentsService 주입

    @Override
    public List<BoardResponseDto> findAll(BoardRequestDto boardRequestDto) {
        return boardMapper.findAll(boardRequestDto);
    }

    @Override
    public BoardResponseDto findByBoardId(int boardId) {
        BoardResponseDto board = boardMapper.findByBoardId(boardId);
        if (board != null) {
            // 첨부파일 조회 및 설정
            List<com.community.community.attachments.dto.AttachmentsResponseDto> attachments = 
                attachmentsService.getAttachments((long) boardId, "board");
            board.setAttachments(attachments);
        }
        return board;
    }

    @Override
    @Transactional
    public int save(BoardRequestDto boardRequestDto, List<MultipartFile> files) {
    	boardMapper.save(boardRequestDto);
        // 게시글 저장 후 생성된 boardId를 가져와 첨부파일 서비스에 전달
        Long boardId = (long) boardRequestDto.getBoardId();
        
        // 첨부파일이 있을 경우에만 saveFiles 호출
        if (files != null && !files.isEmpty()) {
            attachmentsService.saveFiles(files, "board", boardId, boardRequestDto.getWriter());
        }
        
        return boardRequestDto.getBoardId();
    }

    @Override
    public int getTotalCount(BoardRequestDto boardRequestDto) {
        return boardMapper.countAll(boardRequestDto);
    }
    
    @Override
    public int updateView(int boardId) {
    	return boardMapper.updateView(boardId);
    }
}
