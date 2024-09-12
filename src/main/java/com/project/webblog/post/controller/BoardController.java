package com.project.webblog.post.controller;

import com.project.webblog.post.dto.BoardRequestDto;
import com.project.webblog.post.service.BoardService;
import com.project.webblog.user.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/main")
    public ModelAndView main() {
        return new ModelAndView("index");
    }

    @PostMapping("/create-board")
    @Operation(summary = "게시글 작성")
    public ResponseEntity<ApiResponse> createBoard(@RequestBody BoardRequestDto requestDto, @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.createBoard(requestDto, userDetails.getUser());
    }

    @PostMapping("/like-board")
    @Operation(summary = "게시글 좋아요")
    public ResponseEntity<ApiResponse> likePost(@PathVariable Long boardId) {
        return boardService.likePost(boardId);
    }
}
