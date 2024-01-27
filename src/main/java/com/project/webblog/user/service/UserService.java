package com.project.webblog.user.service;

import com.project.webblog.common.exception.ErrorMessage;
import com.project.webblog.common.jwt.JwtUtil;
import com.project.webblog.user.dto.LoginRequestDto;
import com.project.webblog.user.dto.SignupRequestDto;
import com.project.webblog.user.dto.UserResponseDto;
import com.project.webblog.user.entity.User;
import com.project.webblog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.type.ErrorType;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponseDto login(LoginRequestDto userRequestDto, HttpServletResponse servletResponse) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        servletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
        return null;
    }

    @Transactional
    public UserResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();

        Optional<User> foundUsername = userRepository.findByUsername(username);

        if (foundUsername.isPresent()) {
            throw new IllegalArgumentException(ErrorMessage.USERNAME_DUPLICATION.getMessage());
        }

        Optional<User> foundNickname = userRepository.findByNickname(nickname);

        if (foundNickname.isPresent()) {
            throw new IllegalArgumentException(ErrorMessage.NICKNAME_DUPLICATION.getMessage());
        }

        User user = new User(requestDto, password);

        userRepository.save(user);

        return UserResponseDto.of(user);
    }
}
