package com.community.community.admin.controller;

import com.community.community.admin.dto.AdminResponse;
import com.community.community.board.dto.BoardRequestDto;
import com.community.community.board.dto.BoardResponseDto;
import com.community.community.board.service.BoardService;
import com.community.community.notice.dto.NoticeRequestDto;
import com.community.community.notice.dto.NoticeResponseDto;
import com.community.community.notice.service.NoticeService;
import com.community.community.user.dto.UserRequestDto;
import com.community.community.user.dto.UserResponseDto;
import com.community.community.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final BoardService boardService;
    private final NoticeService noticeService;
    private final UserService userService;

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String userManagement(@RequestParam(defaultValue = "1") int page, Model model) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setPage(page);
        List<UserResponseDto> userList = userService.findAll(userRequestDto);
        int totalCount = userService.getTotalCount(userRequestDto);

        int totalPages = (int) Math.ceil((double) totalCount / userRequestDto.getPageSize());

        model.addAttribute("userList", userList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", userRequestDto.getPageSize());
        return "admin/user-list";
    }

    @PostMapping("/users/delete")
    public ResponseEntity<?> deleteUserSoftly(@RequestBody UserRequestDto userRequestDto) {
        log.info("deleteUserSoftly 진입 데이터 확인 ::::: {}", userRequestDto.getUserId());
        try {
            int result = userService.deleteUserSoftly(userRequestDto.getUserId(),userRequestDto.getUseYn());
            if (result > 0) {
                return ResponseEntity.ok().body(new AdminResponse(true, "사용자가 성공적으로 삭제되었습니다."));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AdminResponse(false, "사용자 삭제에 실패했습니다."));
            }
        } catch (Exception e) {
            log.error("사용자 삭제 중 오류 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AdminResponse(false, "사용자 삭제 중 오류가 발생했습니다."));
        }
    }

    @PostMapping("/users/update")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDto userRequestDto) {
        log.info("updateUser 진입 데이터 확인 ::::: {}", userRequestDto);
        try {
            int result = userService.updateUser(userRequestDto);
            if (result > 0) {
                return ResponseEntity.ok().body(new AdminResponse(true, "사용자 정보가 성공적으로 수정되었습니다."));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AdminResponse(false, "사용자를 찾을 수 없거나 정보 수정에 실패했습니다."));
            }
        } catch (Exception e) {
            log.error("사용자 정보 수정 중 오류 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AdminResponse(false, "사용자 정보 수정 중 오류가 발생했습니다."));
        }
    }

    @PostMapping("/users/add")
    public ResponseEntity<?> addUser(@RequestBody UserRequestDto userRequestDto) {
        log.info("addUser 진입 데이터 확인 ::::: {}", userRequestDto);
        try {
            // ID 중복 검사
            if (userService.findById(userRequestDto.getUserId()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new AdminResponse(false, "이미 사용 중인 아이디입니다."));
            }
            
            int result = userService.addUser(userRequestDto);
            if (result > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new AdminResponse(true, "신규 사용자가 성공적으로 등록되었습니다."));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AdminResponse(false, "신규 사용자 등록에 실패했습니다."));
            }
        } catch (Exception e) {
            log.error("신규 사용자 등록 중 오류 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AdminResponse(false, "신규 사용자 등록 중 오류가 발생했습니다."));
        }
    }

    @GetMapping("/boards")
    public String boardManagement(@RequestParam(defaultValue = "1") int page, Model model) {
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setPage(page);
        List<BoardResponseDto> boardList = boardService.findAll(boardRequestDto);
        int totalCount = boardService.getTotalCount(boardRequestDto);

        int totalPages = (int) Math.ceil((double) totalCount / boardRequestDto.getPageSize());

        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", boardRequestDto.getPageSize());
        return "admin/board-list";
    }

    @GetMapping("/notices")
    public String noticeManagement(@RequestParam(defaultValue = "1") int page, Model model) {
        NoticeRequestDto noticeRequestDto = new NoticeRequestDto();
        noticeRequestDto.setPage(page);
        List<NoticeResponseDto> noticeList = noticeService.findAll(noticeRequestDto);
        int totalCount = noticeService.getTotalCount(noticeRequestDto);

        int totalPages = (int) Math.ceil((double) totalCount / noticeRequestDto.getPageSize());

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", noticeRequestDto.getPageSize());
        return "admin/notice-list";
    }

    @GetMapping("/stats")
    public String statistics(Model model) {
        // TODO: 통계 데이터 조회 로직 추가
        return "admin/stats";
    }
}
