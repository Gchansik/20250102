package com.blog.react_spring_blog.dto.request.member;


import com.blog.react_spring_blog.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterDto {
    private String email;
    private String password;
    private String passwordCheck;
    private String username;

    public MemberRegisterDto(String email, String password, String passwordCheck, String username) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.username = username;
    }

    public static Member ofEntity(MemberRegisterDto dto) {
        return  Member.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .roles()
                .build();

    }


}
