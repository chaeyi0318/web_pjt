package com.project.webblog.post.service;

import com.project.webblog.common.exception.ApiResponse;
import com.project.webblog.common.exception.CustomException;
import com.project.webblog.common.exception.ExceptionMessageEnum;
import com.project.webblog.post.dto.BoardRequestDto;
import com.project.webblog.post.entity.Post;
import com.project.webblog.post.repository.BoardRepository;
import com.project.webblog.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

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
        return ApiResponse.toResponseEntity(CREATE_BOARD_SUCCESS);
    }

    @Transactional
    public ResponseEntity<ApiResponse> likePost(Long boardId) {
        Post post = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(BOARD_NOT_FOUND)
        );

        return ApiResponse.toResponseEntity(LIKE_POST_SUCCESS);
    }
}
