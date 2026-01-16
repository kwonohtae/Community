package com.Board.BoardTest.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String userManagement() {
        return "admin/user-list";
    }

    @GetMapping("/posts")
    public String postManagement() {
        return "admin/post-list";
    }

    @GetMapping("/stats")
    public String statistics() {
        return "admin/stats";
    }
}
