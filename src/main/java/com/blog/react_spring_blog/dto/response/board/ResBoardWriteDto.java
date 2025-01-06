package com.blog.react_spring_blog.dto.response.board;

import com.blog.react_spring_blog.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResBoardWriteDto {
    private String title;
    private String content;

    public ResBoardWriteDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Builder
    public static Board ofEntity(ResBoardWriteDto dto) {
        return Board.builder()
                .title(dto.title)
                .content(dto.content)
                .build();
    }


}
