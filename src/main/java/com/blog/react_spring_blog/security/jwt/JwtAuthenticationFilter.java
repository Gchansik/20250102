package com.blog.react_spring_blog.security.jwt;



//Jwt 토큰의 유효성 검사/인증


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter implements OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}") private String HEADER_STRING;
    @Value("${jwt.header}") private String TOKEN_PREFIX;

    //사용자 직접 구현
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Thread currentThread = Thread.currentThread();
        log.info("현재 실행 중인 스레드 : " + currentThread.getName());

        String header = request.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;

        if(header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX, " ");
            try {

            } catch (IllegalArgumentException ex) {
                //사용자 id를 얻는데 실패함
                log.info("사용자 ID를 얻는데 실패함");
                ex.printStackTrace();
            } catch (ExpiredJwtException ex) {
                log.info("토큰 만료");
                ex.printStackTrace();
            } catch (MalformedJwtException ex) {
                log.info("잘못된 JWT(토큰) 만료");
                ex.printStackTrace();
            } catch (Exception ex) {
                log.info("JWT 토큰을 얻을 수 없습니다.");
                ex.printStackTrace();
            }
        } else {
            log.info("JWT는 Bearer로 시작하지 않습니다.");
        }

        if((username != null) && (SecurityContextHolder.getContext().getAuthentication() == null)) {
            log.info(SecurityContextHolder.getContext().getAuthentication.getName());
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenUtil.validateToken(authToken, userDetails)) {

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthroties());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("인증된 사용자" + username + "보안 컨텍스트 설정");
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                log.info("잘못된 JWT 토큰입니다.");
            }
        } else {
            log.info("사용자 이름이 null 이거나, context(컨텍스트)가 null이 아닙니다.");
        }
        filterChain.doFilter(request, response);

    }
}