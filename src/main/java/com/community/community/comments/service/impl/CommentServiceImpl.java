package com.community.community.comments.service.impl;

import com.community.community.comments.dto.CommentRequestDto;
import com.community.community.comments.dto.CommentResponseDto;
import com.community.community.comments.mapper.CommentMapper;
import com.community.community.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public int insertComment(CommentRequestDto commentRequestDto) {
        return commentMapper.insertComment(commentRequestDto);
    }

    @Override
    public List<CommentResponseDto> getCommentsByBoardId(Long boardId) {
        return commentMapper.selectCommentsByBoardId(boardId);
    }

    @Override
    public List<CommentResponseDto> getCommentsByNoticeId(Long noticeId) {
        return commentMapper.selectCommentsByNoticeId(noticeId);
    }

    @Override
    public CommentResponseDto getCommentById(Long commentId) {
        return commentMapper.selectCommentById(commentId);
    }

    @Override
    @Transactional
    public int updateComment(CommentRequestDto commentRequestDto) {
        return commentMapper.updateComment(commentRequestDto);
    }

    @Override
    @Transactional
    public int deleteComment(Long commentId) {
        return commentMapper.deleteComment(commentId);
    }

    @Override
    public int countCommentsByBoardId(Long boardId) {
        return commentMapper.countCommentsByBoardId(boardId);
    }

    @Override
    public int countCommentsByNoticeId(Long noticeId) {
        return commentMapper.countCommentsByNoticeId(noticeId);
    }
}
