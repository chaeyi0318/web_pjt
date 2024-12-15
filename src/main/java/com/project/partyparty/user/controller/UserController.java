package com.project.partyparty.user.controller;

import com.project.partyparty.common.exception.Message;
import com.project.partyparty.user.dto.LoginRequestDto;
import com.project.partyparty.user.dto.SignupRequestDto;
import com.project.partyparty.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name = "user")
@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseBody
    @PostMapping("/signup")
    @Operation(summary = "회원가입", description ="회원가입")
    public ResponseEntity<Message> signup(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @ResponseBody
    @PostMapping("/login")
    @Operation(summary = "로그안", description = "로그인")
    public ResponseEntity<Message> login(@RequestBody LoginRequestDto loginRequestDto, @Parameter(hidden = true) HttpServletResponse response) {
        return userService.login(loginRequestDto);
    }
}
