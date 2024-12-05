package com.project.partyparty.user.dto;

import com.project.partyparty.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private Long userId;
    private String username;
    private String profileImage;
    private String accessToken;
    private String refreshToken;

    public LoginResponseDto(Long userId, String username, String profileImage, String accessToken, String refreshToken) {
        this.username = username;
        this.profileImage = profileImage;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static LoginResponseDto of (User user, String accessToken, String refreshToken) {
        return new LoginResponseDto(
                user.getId(),
                user.getUsername(),
                user.getProfileImage(),
                accessToken,
                refreshToken
        );
    }
}
