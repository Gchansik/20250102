package com.blog.react_spring_blog.dto.request.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateDto {

    private String title;
    private String content;
    private String writerName;

    @Builder
    public BoardUpdateDto(String title, String content, String writerName) {
        this.title = title;
        this.content = content;
        this.writerName = writerName;
    }

    public static BoardUpdateDto createdBoardUpdateDto(String title, String content, String writerName){
        return BoardUpdateDto.builder()
                .title(title)
                .content(content)
                .writerName(writerName)
                .build();
    }
}
