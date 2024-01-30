package com.project.webblog.post.service;

import com.project.webblog.common.exception.ApiResponse;
import com.project.webblog.common.exception.SuccessMessageEnum;
import com.project.webblog.post.dto.BoardRequestDto;
import com.project.webblog.post.entity.Post;
import com.project.webblog.post.repository.BoardRepository;
import com.project.webblog.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import static com.project.webblog.common.exception.ExceptionMessageEnum.*;
import static com.project.webblog.common.exception.SuccessMessageEnum.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public ResponseEntity<ApiResponse> createBoard (BoardRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        boardRepository.save(post);
        return ApiResponse.toResponseEntity(CREATE_BOARD);
    }
}
