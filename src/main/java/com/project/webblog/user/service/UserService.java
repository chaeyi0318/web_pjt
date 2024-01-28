package com.project.webblog.user.service;

import com.project.webblog.common.exception.ApiResponse;
import com.project.webblog.common.exception.ExceptionMessageEnum;
import com.project.webblog.common.exception.SuccessMessageEnum;
import com.project.webblog.common.jwt.JwtUtil;
import com.project.webblog.user.dto.LoginRequestDto;
import com.project.webblog.user.dto.SignupRequestDto;
import com.project.webblog.user.dto.UserResponseDto;
import com.project.webblog.user.entity.User;
import com.project.webblog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.project.webblog.common.exception.ExceptionMessageEnum.*;
import static com.project.webblog.common.exception.SuccessMessageEnum.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> login(LoginRequestDto userRequestDto, HttpServletResponse response) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));

        UserResponseDto responseDto = new UserResponseDto(user.getId(), username, user.getNickname());
        return ApiResponse.toResponseEntity(LOGIN_SUCCESS, responseDto);
    }

    @Transactional
    public ResponseEntity<ApiResponse> signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();

        Optional<User> foundUsername = userRepository.findByUsername(username);

        if (foundUsername.isPresent()) {
            throw new IllegalArgumentException(USERNAME_DUPLICATION.getMessage());
        }

        Optional<User> foundNickname = userRepository.findByNickname(nickname);

        if (foundNickname.isPresent()) {
            throw new IllegalArgumentException(NICKNAME_DUPLICATION.getMessage());
        }

        User user = new User(requestDto, password);

        userRepository.save(user);

        return ApiResponse.toResponseEntity(SIGN_UP_SUCCESS);
    }
}
