package com.community.community.comments.service;

import com.community.community.comments.dto.CommentRequestDto;
import com.community.community.comments.dto.CommentResponseDto;
import java.util.List;

public interface CommentService {
    int insertComment(CommentRequestDto commentRequestDto);
    List<CommentResponseDto> getCommentsByBoardId(Long boardId);
    List<CommentResponseDto> getCommentsByNoticeId(Long noticeId);
    CommentResponseDto getCommentById(Long commentId);
    int updateComment(CommentRequestDto commentRequestDto);
    int deleteComment(Long commentId);
    int countCommentsByBoardId(Long boardId);
    int countCommentsByNoticeId(Long noticeId);
}
