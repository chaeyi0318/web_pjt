package com.project.partyparty.user.service;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.project.partyparty.common.exception.ExceptionMessage;
import com.project.partyparty.common.exception.Message;
import com.project.partyparty.common.exception.SuccessMessage;
import com.project.partyparty.common.security.JwtUtil;
import com.project.partyparty.common.security.RefreshToken;
import com.project.partyparty.common.security.RefreshTokenService;
import com.project.partyparty.user.dto.LoginRequestDto;
import com.project.partyparty.user.dto.LoginResponseDto;
import com.project.partyparty.user.dto.SignupRequestDto;
import com.project.partyparty.user.dto.SignupResponseDto;
import com.project.partyparty.user.entity.User;
import com.project.partyparty.user.entity.UserRoleEnum;
import com.project.partyparty.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.project.partyparty.common.exception.SuccessMessage.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<Message> signup(SignupRequestDto signupRequestDto) {
        if (userRepository.existsByUsername(signupRequestDto.getUsername())) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATE_USERNAME.getDetail());
        }

        if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATE_EMAIL.getDetail());
        }

        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        User newUser = User.builder()
                .username(signupRequestDto.getUsername())
                .email(signupRequestDto.getEmail())
                .nickname(signupRequestDto.getNickname())
                .password(encodedPassword)
                .profileImage(signupRequestDto.getProfileImage())
                .role(UserRoleEnum.USER)
                .build();

        User savedUser = userRepository.save(newUser);

        // 응답 데이터 생성
        SignupResponseDto signupResponseDto = new SignupResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getNickname());

        // 성공 메시지 생성
        SuccessMessage successMessage = new SuccessMessage(HttpStatus.CREATED, "회원가입 성공");

        // ResponseEntity 반환
        return Message.toResponseEntity(successMessage, signupResponseDto);
    }

    @Transactional
    public ResponseEntity<Message> login(LoginRequestDto loginRequestDto) {
        return null;
    }

    public void logout(String username) {
        refreshTokenService.deleteByUsername(username);
    }
}
