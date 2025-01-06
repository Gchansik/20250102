package com.blog.react_spring_blog.service;

import com.blog.react_spring_blog.common.exception.ResourceNotFoundException;
import com.blog.react_spring_blog.dto.request.comment.CommentDto;
import com.blog.react_spring_blog.dto.response.comment.ResCommentDto;
import com.blog.react_spring_blog.entity.Board;
import com.blog.react_spring_blog.entity.Comment;
import com.blog.react_spring_blog.entity.Member;
import com.blog.react_spring_blog.repository.BoardRepository;
import com.blog.react_spring_blog.repository.CommentRepository;
import com.blog.react_spring_blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    public Page<ResCommentDto> getAllComments(
        Pageable pageable, Long boardId) {

        Page<Comment> comments = commentRepository.getAllWithMemberAndBoard(pageable, boardId);

        List<ResCommentDto> commentList = comments.getContent().stream()
                .map(ResCommentDto::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(commentList,pageable,comments.getTotalElements());
    }

    //답글 작성 write
    public ResCommentDto write(Long boardId, Member member, CommentDto writeDto) {

        Board board = boardRepository.findById(boardId).orelseThrow(
                () -> new ResourceNotFoundException("Board", "Board ID", String.valueOf(boardId))
        );

        Member commentWriter = memberRepository
                .findById(member.getId()).orElseThrow(
                        () -> new ResourceNotFoundException("Member", "Member Id",
                                String.valueOf(member.getId()))
                );
        Comment comment = CommentDto.ofEntity(writeDto);
        comment.setBoard(board);
        comment.setMember(commentWriter);

        Comment saveComment = commentRepository.save(comment);
        return ResCommentDto.fromEntity(saveComment);
    }

    public ResCommentDto update(Long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findByIdWithMemberAndBoard(commentId).orElseThrow(
                () -> ResourceNotFoundException("Comment", "Comment Id", String.valueOf(commentId))
        );

        comment.update(commentDto.getContent());
        return ResCommentDto.fromEntity(comment);
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }


}
