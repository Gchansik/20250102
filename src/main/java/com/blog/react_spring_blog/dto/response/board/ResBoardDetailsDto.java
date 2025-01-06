package com.blog.react_spring_blog.dto.response.board;

import com.blog.react_spring_blog.dto.response.comment.ResCommentDto;
import com.blog.react_spring_blog.dto.response.file.ResBoardDetailsFileDto;
import com.blog.react_spring_blog.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ResBoardDetailsDto {
    private Long boardId;
    private String title;
    private String content;
    private int viewCount;
    private String writerName; //작성자
    private String createdDate; //생성일자
    private String modifiedDate; //수정 후 저장

    //comments
    private List<ResCommentDto> comments;

    //file
    private List<ResBoardDetailsFileDto> files;


    @Builder
    public ResBoardDetailsDto(Long boardId, String title, String content, int viewCount, String writerName, String createdDate, String modifiedDate, List<ResCommentDto> comments, List<ResBoardDetailsFileDto> files) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.writerName = writerName;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.comments = comments;
        this.files = files;
    }


    //fromEntity() 빌더 패턴 + 필요사항(회원+파일+댓글)
    public static ResBoardDetailsDto fromEntity(Board board){
        return ResBoardDetailsDto.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(board.getViewCount())
                .writerName(board.getMember().getUsername())//★회원/사용자이름
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .comments(board.getComments().stream()
                        .map(ResCommentDto::fromEntity)
                        .collect(Collectors.toList()))
                .files(board.getFiles().stream()
                        .map(ResBoardDetailsFileDto::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }

}
