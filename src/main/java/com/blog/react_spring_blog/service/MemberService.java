package com.blog.react_spring_blog.service;

import com.blog.react_spring_blog.common.exception.MemberException;
import com.blog.react_spring_blog.common.exception.ResourceNotFoundException;
import com.blog.react_spring_blog.dto.request.member.MemberLoginDto;
import com.blog.react_spring_blog.dto.request.member.MemberRegisterDto;
import com.blog.react_spring_blog.dto.request.member.MemberUpdateDto;
import com.blog.react_spring_blog.dto.response.member.MemberResponseDto;
import com.blog.react_spring_blog.dto.response.member.MemberTokenDto;
import com.blog.react_spring_blog.entity.Member;
import com.blog.react_spring_blog.repository.MemberRepository;
import com.blog.react_spring_blog.security.jwt.JwtTokenUtil;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final JwtTokenUtil jwtTokenUtil;

    private void authenticate(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pwd));
        } catch (DisabledException e) {
            throw new MemberException("인증되지 않은 아이디입니다.", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            throw new MemberException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
    }


    private void isExistUserEmail(String email) {
        if(memberRepository.findByEmail(email).isPresent()){
            throw new MemberException("이미 사용 중인 이메일입니다.");
        }
    }


    private void checkPassword(String password, String passwordCheck) {
        if(!password.equals(passwordCheck)) {
            throw new MemberException("패스워드 불일치", HttpStatus.BAD_REQUEST);
        }
    }

    private void checkEncodePassword(String rawPassword, String encodePassword) {
        if (!encoder.matches(rawPassword, encodePassword)) {
            throw new MemberException()
        }
    }

    public HttpStatus checkIdDuplicate(String email) {
        isExistUserEmail(email);
        return  HttpStatus.OK;
    }

    public MemberResponseDto register(MemberRegisterDto registerDto) {
        isExistUserEmail(registerDto.getEmail());

        checkPassword(registerDto.getPassword(), registerDto.getPassword());

        String encodePwd = encoder.encode(registerDto.getPassword());
        registerDto.setPassword(encodePwd);

        Member saveMember = memberRepository.save(MemberRegisterDto.ofEntity(registerDto));

        return MemberRegisterDto.fromEntity(saveMember);
    }

    public MemberTokenDto login(MemberLoginDto loginDto) {
        authenticate(loginDto.getEmail(), loginDto.getPassword());

        UserDetails userDetails = userDetailService.loadUserByUsername(loginDto.getEmail());

        checkEncodePassword(loginDto.getPassword());
    }

    public MemberResponseDto check(Member member, String password) {
        Member checkMember = (Member) userDetailService.loadUserByUsername(member.getEmail());

        checkEncodePassword(password, checkMember.getPassword());

        return MemberResponseDto.fromEntity(checkMember);
    }

    public MemberResponseDto update(Member member, MemberUpdateDto updateDto) {
        checkPassword(updateDto.getPassword(), updateDto.getPasswordCheck());

        String encodePwd = encoder.encode(updateDto.getPassword());

        Member updateMember = memberRepository.findByEmail(member.getEmail()).orElseThrow( () -> new ResourceNotFoundException("Member", "Member Email", member.getEmail()));

        updateMember.update(encodePwd, updateDto.getUsername());

        return
    }




}