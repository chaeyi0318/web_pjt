package com.project.partyparty.user.entity;

import com.project.partyparty.common.entity.TimeStamped;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "users")
public class User extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String profileImage;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;
}
