package com.blog.react_spring_blog.dto.response.member;

import com.blog.react_spring_blog.dto.request.member.MemberLoginDto;
import com.blog.react_spring_blog.dto.request.member.MemberRegisterDto;
import com.blog.react_spring_blog.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    private String email;
    private String username;

    public MemberResponseDto(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public static MemberRegisterDto fromEntity(Member member) {
        return MemberRegisterDto.builder()
                .email(member.getEmail())
                .username(member.getUsername())

    }
}
