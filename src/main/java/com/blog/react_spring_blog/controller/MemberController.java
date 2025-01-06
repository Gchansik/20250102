package com.blog.react_spring_blog.controller;


import com.blog.react_spring_blog.dto.request.member.MemberLoginDto;
import com.blog.react_spring_blog.dto.request.member.MemberRegisterDto;
import com.blog.react_spring_blog.dto.request.member.MemberUpdateDto;
import com.blog.react_spring_blog.dto.response.member.MemberResponseDto;
import com.blog.react_spring_blog.dto.response.member.MemberTokenDto;
import com.blog.react_spring_blog.entity.Member;
import com.blog.react_spring_blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/check")
    public ResponseEntity<?> checkIdDuplicate(@RequestParam String email){
        memberService.checkIdDuplicate(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/register")
    public ResponseEntity<MemberResponseDto> register(@RequestBody MemberRegisterDto memberRegisterDto) {
        MemberResponseDto successMember = memberService.register(memberRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMember);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberTokenDto> login(@RequestBody MemberLoginDto memberLoginDto) {
        MemberTokenDto loginDto = memberService.login(memberLoginDto);

        return ResponseEntity.status(HttpStatus.OK).header(loginDto.getToken()).body(loginDto);
    }

    @PostMapping("/checkPwd")
    public ResponseEntity<MemberResponseDto> check(
            @AuthenticationPrincipal Member member,
            @RequestBody Map<String,String> request) {

        String password = request.get("password");
        MemberResponseDto memberInfo = memberService.check(member, password);
        return ResponseEntity.status(HttpStatus.OK).body(memberInfo);
    }

    @PutMapping("/update")
    public ResponseEntity<MemberResponseDto> update(@AuthenticationPrincipal Member member, @RequestBody MemberUpdateDto memberUpdateDto) {
        MemberResponseDto memberUpdate = memberService.update(member,memberUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(memberUpdate);
    }


}