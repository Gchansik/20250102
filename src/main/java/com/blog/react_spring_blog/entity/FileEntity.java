package com.blog.react_spring_blog.entity;

import com.blog.react_spring_blog.common.BaseTimeEntity;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FILE")
@Getter
@NoArgsConstructor
public class FileEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name="FILE_ID")
    private Long id;

    @Column(name="ORIGINAL_FILE_NAME")
    private String originalFileName;

    @Column(name="FILE_TYPE")
    private String fileType;

    @Column(name="FILE_PATH")
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    public Board board;


    public FileEntity(Long id, String originalFileName, String fileType, String filePath, Board board) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.board = board;
    }

    public void SetMappingBoard(Board board) {
        this.board = board;
        member.getBoards().add(this);
    }

    public void upViewCount() {
        this.viewCount ++;
    }

    public void update(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }


}
