package com.blog.react_spring_blog.dto.request.member;

import lombok.Builder;

public class MemberLoginDto {

    private String email;
    private String password;
    private String username;

    @Builder
    public MemberLoginDto(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }


}
