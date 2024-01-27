package com.project.webblog.user.dto;

import com.project.webblog.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;

    private String username;

    private String nickname;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user);
    }
}
