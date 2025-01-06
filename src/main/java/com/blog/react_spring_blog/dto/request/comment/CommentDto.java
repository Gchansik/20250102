package com.blog.react_spring_blog.dto.request.comment;


import com.blog.react_spring_blog.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private String content;

    public CommentDto(String content) {
        this.content = content;
    }

    //Entity에서 DTO로 변환
    public static Comment ofEntity(CommentDto dto) {
        return Comment.builder()
                .content(dto.getContent())
                .build();
    }
}
