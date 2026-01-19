package com.community.community.admin.controller;

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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
