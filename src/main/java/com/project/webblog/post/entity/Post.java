package com.project.webblog.post.entity;

import com.project.webblog.common.entity.TimeStamped;
import com.project.webblog.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity(name = "post")
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE post SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Post extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @ManyToOne
    private User user;

    public Post (String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
