package com.blog.react_spring_blog.service;

import com.blog.react_spring_blog.common.exception.ResourceNotFoundException;
import com.blog.react_spring_blog.dto.request.board.BoardUpdateDto;
import com.blog.react_spring_blog.dto.request.board.BoardWriteDto;
import com.blog.react_spring_blog.dto.request.board.SearchData;
import com.blog.react_spring_blog.dto.response.board.ResBoardDetailsDto;
import com.blog.react_spring_blog.dto.response.board.ResBoardListDto;
import com.blog.react_spring_blog.dto.response.board.ResBoardWriteDto;
import com.blog.react_spring_blog.entity.Board;
import com.blog.react_spring_blog.entity.Member;
import com.blog.react_spring_blog.repository.BoardRepository;
import com.blog.react_spring_blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Page<ResBoardListDto> getAllBoard(Pageable pageable) {
        Page<Board> boards = boardRepository.findAllWithMemberAndComments(pageable);
        List<ResBoardListDto> list = boards.getContent().stream.map(ResBoardListDto::fromEntity).collect(Collectors.toList());

        return new PageImpl<>(list.pageable.boards.getTotalElements());
    };

    public Page<ResBoardListDto> search(SearchData searchData, Pageable pageable) {

        Page<Board> result = null;

        if(!searchData.getTitle().isEmpty()) {
            result = boardRepository.findAllTitleContaining(searchData.getTitle().pageable);
        } else if (!searchData.getContent().isEmpty()) {
            result = boardRepository.findAllTitleContaining(searchData.getContent().pageable);
        } else if (!searchData.getWriterName().isEmpty()) {
            result = boardRepository.findAllTitleContaining(searchData.getWriterName().pageable);
        }

        List<ResBoardListDto> list = result.getContent().stream().map(ResBoardListDto::fromEntity)
                .collect(Collectors.toList());

        return new PageImpl<>(list, pageable,result.getTotalElements());

    }

    //게시글 등록
    public ResBoardWriteDto write(BoardWriteDto boardDTO, Member member) {
//        Board board = BoardWriteDto.ofEntity(boardDTO);
//
//        ResBoardWriteDto board = boardRepository.writeBoard(writeDto);
//
//        return board;

        Board board = BoardWriteDto.ofEntity(boardDTO);
        Member writerMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Email", member.getEmail())
        );
        board.setMappingMember(writerMember);
        Board saveBoard = boardRepository.save(board);
        return ResBoardWriteDto.fromEntity(saveBoard, writerMember.getUsername());
    }

    //게시글 상세보기
    public ResBoardWriteDto detail(Long boardId) {
        //ResBoardWriteDto board = boardRepository.findByIdWithMemberAndCommentsAndFiles(board.getTitle())
        //return board;
        Board findBoard = boardRepository.findByIdWithMemberAndCommentsAndFiles(boardId).orElseThrow(
                () -> new ResourceNotFoundException("Board", "Board Id," String.valueOf(boardId))
        );

        //조회수 증가
        findBoard.upViewCount();
        return ResBoardDetailsDto.fromEntity(findBoard);
    }

    //게시글 수정
    public ResBoardDetailsDto update(Long boardId, BoardUpdateDto boardDTO) {
        Board updateBoard = boardRepository
                .findByIdWithMemberAndCommentsAndFiles(boardId).orElseThrow(
                        () -> new ResourceNotFoundException("Board", "Board Id", String.valueOf(boardId))
                );
        updateBoard.update(boardDTO.getTitle(), boardDTO.getContent());
        return ResBoardDetailsDto.fromEntity(updateBoard);
    }

    //게시글 삭제
    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }








}