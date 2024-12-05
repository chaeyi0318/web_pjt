package com.project.partyparty.user.service;

import com.project.partyparty.common.security.JwtUtil;
import com.project.partyparty.common.security.RefreshToken;
import com.project.partyparty.common.security.RefreshTokenService;
import com.project.partyparty.user.dto.LoginRequestDto;
import com.project.partyparty.user.dto.LoginResponseDto;
import com.project.partyparty.user.entity.User;
import com.project.partyparty.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow();

        String username = authentication.getName();
        String accessToken = jwtUtil.generateAccessToken(username);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(username);

        return new LoginResponseDto(user.getId(), user.getUsername(), user.getProfileImage(), accessToken, refreshToken.getToken());
    }

    public void logout(String username) {
        refreshTokenService.deleteByUsername(username);
    }
}
