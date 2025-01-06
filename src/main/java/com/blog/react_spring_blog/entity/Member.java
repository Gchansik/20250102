package com.blog.react_spring_blog.entity;

import com.blog.react_spring_blog.common.BaseTimeEntity;
//import jakarta.persistence.Entity;
import com.blog.react_spring_blog.common.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    private Role roles;

    //JPA 연관관계
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY){
        public List<Board> boards = new ArrayList<>();

    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY){
        public List<Comment> comments = new ArrayList<>();

    }


    //(1) 게시판
    //(2) 댓글

    //생성자 Builder
    @Builder
    public Member(Long id, String email, String password, String username, Role roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }
    
    //회원정보수정 - 메서드
    public void update(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
