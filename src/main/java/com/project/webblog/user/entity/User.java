package com.project.webblog.user.entity;

import com.project.webblog.common.entity.TimeStamped;
import com.project.webblog.post.entity.Post;
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

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

//    public User (String nickname, String email, String userId, String password) {
//        this.nickname = nickname;
//        this.email = email;
//        this.userId = userId;
//        this.password = password;
//    }

    public User (String nickname, String userId, String password, UserRoleEnum role) {
        this.nickname = nickname;
        this.userId = userId;
        this.password = password;
        this.role = role;
    }
}
