package com.project.webblog.user.controller;

import com.project.webblog.user.dto.LoginRequestDto;
import com.project.webblog.user.dto.SignupRequestDto;
import com.project.webblog.user.security.UserDetailsImpl;
import com.project.webblog.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequestDto requestDto, @Parameter(hidden = true) HttpServletResponse response) {
        return userService.login(requestDto,response);
    }

    @GetMapping("/info")
    @ResponseBody
    public String getUserName(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails.getUsername();
    }
}
