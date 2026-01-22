package com.community.community.comments.mapper;

import com.community.community.comments.dto.CommentRequestDto;
import com.community.community.comments.dto.CommentResponseDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CommentMapper {
    int insertComment(CommentRequestDto commentRequestDto);
    List<CommentResponseDto> selectCommentsByBoardId(Long boardId);
    List<CommentResponseDto> selectCommentsByNoticeId(Long noticeId);
    CommentResponseDto selectCommentById(Long commentId);
    int updateComment(CommentRequestDto commentRequestDto);
    int deleteComment(Long commentId);
    int countCommentsByBoardId(Long boardId);
    int countCommentsByNoticeId(Long noticeId);
}
