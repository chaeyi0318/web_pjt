package com.project.partyparty.common.security;

import com.project.partyparty.common.entity.TimeStamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity(name = "refresh_tokens")
@NoArgsConstructor
public class RefreshToken extends TimeStamped {
    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    public RefreshToken(String username, String token, Instant expiryDate) {
        this.username = username;
        this.token = token;
        this.expiryDate = expiryDate;
    }
}
