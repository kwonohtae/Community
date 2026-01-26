package com.community.community.board.service.impl;

import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;
import com.community.community.board.mapper.BoardMapper;
import com.community.community.board.service.BoardService;
import com.community.community.attachments.dto.AttachmentsResponseDto;
import com.community.community.attachments.service.AttachmentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
    private final AttachmentsService attachmentsService;

    @Override
    public List<BoardResponseDto> findAll(BoardRequestDto boardRequestDto) {
        return boardMapper.findAll(boardRequestDto);
    }

    @Override
    public BoardResponseDto findByBoardId(Long boardId) { // int -> Long 변경
        BoardResponseDto board = boardMapper.findByBoardId(boardId);
        if (board != null) {
            // 첨부파일 조회 및 설정
            List<AttachmentsResponseDto> attachments = 
                attachmentsService.getAttachments(boardId, "board"); // (long) 캐스팅 제거
            board.setAttachments(attachments);
        }
        return board;
    }

    @Override
    @Transactional
    public int save(BoardRequestDto boardRequestDto, List<MultipartFile> files) {
    	boardMapper.save(boardRequestDto);
        // 게시글 저장 후 생성된 boardId를 가져와 첨부파일 서비스에 전달
        Long boardId = boardRequestDto.getBoardId(); // (long) 캐스팅 제거
       
        // 첨부파일이 있을 경우에만 saveFiles 호출
        if (files != null && !files.isEmpty()) {
        	 log.info("첨부파일 내역 확인 :::: {}", files.get(0).getOriginalFilename());
            attachmentsService.saveFiles(files, "board", boardId, boardRequestDto.getWriter());
        }
        
        return boardRequestDto.getBoardId().intValue(); // 반환 타입을 int로 유지하기 위해 intValue() 호출
    }

    @Override
    public int getTotalCount(BoardRequestDto boardRequestDto) {
        return boardMapper.countAll(boardRequestDto);
    }
    
    @Override
    public int updateView(Long boardId) { // int -> Long 변경
    	return boardMapper.updateView(boardId);
    }

    @Override
    public List<BoardResponseDto> findRepliesByParentId(Long parentId) { // 추가
        return boardMapper.findRepliesByParentId(parentId);
    }
}