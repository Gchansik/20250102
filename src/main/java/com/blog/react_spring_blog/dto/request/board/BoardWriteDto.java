package com.blog.react_spring_blog.dto.request.board;

import com.blog.react_spring_blog.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardWriteDto {
    private String title;
    private String content;
    private String writerName;

    @Builder
    public BoardWriteDto(String title, String content, String writerName) {
        this.title = title;
        this.content = content;
        this.writerName = writerName;
    }

    @Builder
    public static Board ofEntity(BoardWriteDto dto) {
        return Board.builder()
                .title(dto.title)
                .content(dto.content)
                .build();
    }

}



