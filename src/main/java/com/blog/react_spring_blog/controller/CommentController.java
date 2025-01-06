package com.blog.react_spring_blog.controller;

import com.blog.react_spring_blog.dto.request.comment.CommentDto;
import com.blog.react_spring_blog.dto.response.comment.ResCommentDto;
import com.blog.react_spring_blog.entity.Member;
import com.blog.react_spring_blog.service.BoardService;
import com.blog.react_spring_blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/board/{boardId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/list")
    public ResponseEntity<Page<ResCommentDto>> commentList(@PathVariable Long boardId, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) pageable){
        Page<ResCommentDto> commentList = commentService.getAllComments(pageble, boardId);

        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    @PostMapping("/write")
    public ResponseEntity<ResCommentDto> write(
            @AuthenticationPrincipal Member member,
            @PathVariable Long boardId,
            @RequestBody CommentDto commentDto
            ) {
        ResCommentDto saveCommentDto = commentService.write(boardId, member, commentDto);

        return ResponseEntity.status(HttpStatus.OK).body(saveCommentDto);
    }

    @PatchMapping("/update/{commentId}")
    public ResponseEntity<ResCommentDto> update(
            @PathVariable Long commentId,
            @RequestBody CommentDto commentDto) {

        ResCommentDto updateCommentDto = commentService.update(commentId, commentDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateCommentDto);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Long> delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}