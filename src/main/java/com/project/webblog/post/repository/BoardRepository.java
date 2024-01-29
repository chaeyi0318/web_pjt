package com.project.webblog.post.repository;

import com.project.webblog.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Post, Long> {
}
