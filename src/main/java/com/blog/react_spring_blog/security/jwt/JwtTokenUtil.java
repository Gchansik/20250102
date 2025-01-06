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
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = -2550185165626007488L;;

    @Value("${jwt.tokenExpirationtime}") private Integer tokenExpirationTime;
    @Value("${jwt.secret}") private String secret;

    //JST 토큰에서, 사용자 이름 추출
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //JWT 토큰에서, 만료 날짜 추출
    public Date getExpirationDateFromToken() {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimFromToken(token);
        return JWTs.parser().setSignKey(secret).parseClaimsJwt(token).getBody();
    }

    private Claims getAllClaimFromToken(String token) {
        return Jwts.parser().setSignKey(secret).parseClaimsJwt(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public String generateToken(Userdetails userdetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userdetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUserName && !isTokenExpired(token)));
    }

}