package com.project.partyparty.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupResponseDto {
    private Long id;

    private String username;

    private String nickname;
}
