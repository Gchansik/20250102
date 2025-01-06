package com.blog.react_spring_blog.dto.request.board;

import com.blog.react_spring_blog.dto.request.comment.CommentDto;
import com.blog.react_spring_blog.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchData {

    private String title;
    private String content;
    private String writerName;

    @Builder
    public SearchData(String title, String content, String writerName) {
        this.title = title;
        this.content = content;
        this.writerName = writerName;
    }

    //Entity에서 DTO로 변환
    public static Comment ofEntity(CommentDto dto) {
        return Comment.builder()
                .content(dto.getContent())
                .build();
    }

    public static SearchData createdSearchData(String title, String content, String writerName){
        return SearchData.builder()
                .title(title)
                .content(content)
                .writerName(writerName)
                .build();
    }

}
