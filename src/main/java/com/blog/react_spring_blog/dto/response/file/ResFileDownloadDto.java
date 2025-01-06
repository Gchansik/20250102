package com.blog.react_spring_blog.dto.response.file;

import com.blog.react_spring_blog.entity.FileEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * -Response-
 * 파일 다운로드 후 응답 dto
 */

@Getter
@Setter
@NoArgsConstructor
public class ResFileDownloadDto {

    //이름,타입,내용
    private String filename;
    private String fileType;
    private byte[] content;

    @Builder
    public ResFileDownloadDto(String filename, String fileType, byte[] content) {
        this.filename = filename;
        this.fileType = fileType;
        this.content = content;
    }

    //fromFileResource
    public static ResFileDownloadDto fromFileResource(
            FileEntity file, String contentType, byte[] content){
        return ResFileDownloadDto.builder()
                .filename(file.getOriginalFileName())
                .fileType(contentType)
                .content(content)
                .build();
    }
}






