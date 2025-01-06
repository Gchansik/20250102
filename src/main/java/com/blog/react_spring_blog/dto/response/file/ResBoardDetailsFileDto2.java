package com.blog.react_spring_blog.dto.response.file;


import com.blog.react_spring_blog.entity.FileEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
    -Response
    게시글 상세 정보에 포함될 file 정보 dto
*/

@Getter
@Setter
@NoArgsConstructor
public class ResBoardDetailsFileDto2 {

    private Long fileId;
    private String originalFileName;
    private String fileType;

    @Builder
    public ResBoardDetailsFileDto2(Long fileId, String originalFileName, String fileType) {
        this.fileId = fileId;
        this.originalFileName = originalFileName;
        this.fileType = fileType;
    }

    public static ResBoardDetailsFileDto2 fromEntity(FileEntity file) {
        return ResBoardDetailsFileDto2.builder()
                .fileId(file.getId())
                .originalFileName(file.getOriginalFileName())
                .fileType(file.getFileType())
                .build();
    }

}

