package com.community.community.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.community.user.dto.UserRequestDto;
import com.community.community.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest; // Added import
import jakarta.servlet.http.HttpServletResponse; // Added import
import jakarta.servlet.http.HttpSession; // Added import for HttpSession
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager; // Inject AuthenticationManager

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/joinPage")
    public String joinPage() {
        return "user/join";
    }

    @GetMapping("/find")
    public String findPage() {
        return "user/find";
    }

    @PostMapping("/logout") // Changed to PostMapping
    public ResponseEntity<?> logoutPage(HttpServletRequest request, HttpServletResponse response) { // Added request and response
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "로그아웃 되었습니다.");
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto userRequestDto, HttpSession session) { // Added HttpSession
    	Map<String, Object> response = new HashMap<>();
        boolean loginSuccess = userService.login(userRequestDto);

        if (loginSuccess) {
            // Spring Security authentication
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userRequestDto.getUserId(), userRequestDto.getPassword());
            
            try {
//            	log.info("1111");
//                Authentication authentication = authenticationManager.authenticate(authToken);
//                log.info("2222");
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                log.info("3333");
//                // Store userId in session manually, if needed for direct access
                session.setAttribute("userId", userRequestDto.getUserId()); 
                
                // Spring Security manages isLoggedIn, so no need for manual session.setAttribute("isLoggedIn", true);

                response.put("success", true);
                response.put("message", "로그인 성공!");
            } catch (Exception e) {
                log.error("Authentication failed for user {}: {}", userRequestDto.getUserId(), e.getMessage());
                response.put("success", false);
                response.put("message", "아이디 또는 비밀번호가 올바르지 않습니다.");
            }
        } else {
            response.put("success", false);
            response.put("message", "아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        return ResponseEntity.ok(response);
    }
}
