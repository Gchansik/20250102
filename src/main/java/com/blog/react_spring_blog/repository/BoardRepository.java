package com.blog.react_spring_blog.repository;

import com.blog.react_spring_blog.entity.Board;

import java.awt.print.Pageable;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByIdWithMemberAndCommentsAndFiles(Long boardId);

    @Query
    Page<Board> findAllWithMemberAndComments(Pageable pageable);

    Page<Board> findAllTitleContaining(String title, Pageable pageable);



}