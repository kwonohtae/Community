package com.community.community.comments.controller;

import com.community.community.comments.dto.CommentRequestDto;
import com.community.community.comments.dto.CommentResponseDto;
import com.community.community.comments.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록
    @PostMapping("/insert")
    public ResponseEntity<String> createComment(@RequestBody CommentRequestDto commentRequestDto, HttpSession session) {
    	
    	log.info("createComment 진입 데이터 확인 ::: {}", commentRequestDto);
    	
//        String userId = (String) session.getAttribute("userId");
        String userId = "Test";
		/*
		 * if (userId == null) { log.info("1"); return
		 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다."); }
		 */
        commentRequestDto.setUserId(userId);
        commentRequestDto.setWriter(userId);
        commentRequestDto.setInsertUser(userId);

        if (commentRequestDto.getBoardId() == null && commentRequestDto.getNoticeId() == null) {
            return ResponseEntity.badRequest().body("게시글 또는 공지사항 ID가 필요합니다.");
        }
        
        // boardId와 noticeId 둘 중 하나만 존재하도록
        if (commentRequestDto.getBoardId() != null && commentRequestDto.getNoticeId() != null) {
            return ResponseEntity.badRequest().body("게시글과 공지사항 ID를 동시에 지정할 수 없습니다.");
        }

        int result = commentService.insertComment(commentRequestDto);
        if (result > 0) {
        	log.info("!!!!!!!!!!!!!!!!!!!!");
            return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 성공적으로 등록되었습니다.");
        } else {
        	log.info("@@@@@@@@@@@@@@@@@@@@");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 등록에 실패했습니다.");
        }
    }

    // 게시글 댓글 조회
    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByBoardId(@PathVariable Long boardId) {
    	log.info("getCommentsByBoardId 진입 확인 :::: {}" , boardId);
        List<CommentResponseDto> comments = commentService.getCommentsByBoardId(boardId);
        return ResponseEntity.ok(comments);
    }

    // 공지사항 댓글 조회
    @GetMapping("/notice/{noticeId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByNoticeId(@PathVariable Long noticeId) {
    	log.info("getCommentsByNoticeId 진입 확인 :::: {}" , noticeId);
    	List<CommentResponseDto> comments = commentService.getCommentsByNoticeId(noticeId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 수정
    @PutMapping("/update/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        CommentResponseDto existingComment = commentService.getCommentById(commentId);
        if (existingComment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("댓글을 찾을 수 없습니다.");
        }
        if (!Objects.equals(existingComment.getUserId(), userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("댓글 수정 권한이 없습니다.");
        }

        commentRequestDto.setCommentId(commentId);
        commentRequestDto.setUpdateUser(userId);

        int result = commentService.updateComment(commentRequestDto);
        if (result > 0) {
            return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정에 실패했습니다.");
        }
    }

    // 댓글 삭제
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        CommentResponseDto existingComment = commentService.getCommentById(commentId);
        if (existingComment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("댓글을 찾을 수 없습니다.");
        }
        // 관리자 권한 확인 로직 추가 가능
        if (!Objects.equals(existingComment.getUserId(), userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("댓글 삭제 권한이 없습니다.");
        }

        int result = commentService.deleteComment(commentId);
        if (result > 0) {
            return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제에 실패했습니다.");
        }
    }
}
