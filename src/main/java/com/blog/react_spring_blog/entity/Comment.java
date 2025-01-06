package com.blog.react_spring_blog.entity;

import com.blog.react_spring_blog.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    private String content;

    @ManyToOne(fect=FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    public Member member;

    @ManyToOne(fect=FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    public Board board;


    @Builder
    public Comment(Long id, String content, Member member, Board board) {
        this.id = id;
        this.content = content;
        this.member = member;
        this.board = board;
    }

    public void setMember(Board board) {
        this.board = board;
        board.getBoard();
    }


    public void setMember(Member member) {
        this.member = member;
        member.getMember();
    }

    public void update(String content) {
        this.content = content;
    }


}
