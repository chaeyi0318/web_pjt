package com.project.webblog.user.entity;

import com.project.webblog.common.entity.TimeStamped;
import com.project.webblog.post.entity.Post;
import com.project.webblog.user.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

//    @Column(nullable = false, unique = true)
//    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    public User (SignupRequestDto requestDto, String encodedPassword) {
        this.username = requestDto.getUsername();
        this.password = encodedPassword;
        this.nickname = requestDto.getNickname();
        this.role = UserRoleEnum.USER;
    }
}
