package com.community.community.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/loginPage")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/joinPage")
    public String joinPage() {
        return "user/join";
    }

    @GetMapping("/findPage")
    public String findPage() {
        return "user/find";
    }
}
