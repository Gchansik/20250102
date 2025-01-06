package com.blog.react_spring_blog.entity;

import com.blog.react_spring_blog.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;

    @Column(name = "VIEW_COUNT")
    private int viewCount;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fect = FetchType.LAZY)
    @BatchSize(size = 10)
    public list<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fect = FetchType.LAZY)
    @BatchSize(size = 10)
    public list<FileEntity> files = new ArrayList<>();


    @ManyToOne(fect = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    public Member member;

    @Builder
    public Board(Long id, String title, String content, int viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }

    public void setMappingMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

}