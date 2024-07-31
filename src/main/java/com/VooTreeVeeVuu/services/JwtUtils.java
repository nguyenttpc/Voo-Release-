package com.VooTreeVeeVuu.services;

import com.VooTreeVeeVuu.domain.entity.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtUtils {
    private static final String SECRET_KEY = "dXhwbW15P0RCRmNZVC8/SGYwTW5sODdqNUpuemcwSmM4bHlmPWw9WjBZS2FvN21RNnk9TUNEdjdHZHohTUpTTk1reXN0SEtDbDVzTUpEQmVSLXEtWU93UFo1b2dxPVFESlpSSWhFWWh4Mlk3OVpuY1BmY2MwTENudXJOZmp6WkdBOFl4clNEZm9hP2RIVG1Gbk5KISFpRkRiT3p6UDQyYVNUdGFQWWVRN2ktZXRZd0wweVg2TmEzNkFrcE9zdGJ1SHlaNzlBZW1ZbFRWb0V0clpzR2JKPTchUDBUQUUwNXczUjRVcDFodEg1YUJLSDA0bkUvQ0hyOEJLWW5pQk9aVw==";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Trong phương thức generateToken của JwtService

    public static String generateToken(Account account) {
        return generateToken(new HashMap<>(), account);
    }

    public static String generateToken(Map<String, Object> extraClaims, Account account) {
        List<String> roles = account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        String email = account.getEmail();
        Long id = account.getId();
        String phone = account.getPhoneNum();
        Long user_id = account.getUser().getId();
        System.out.println(phone);

        return Jwts.builder().setClaims(extraClaims).setSubject(account.getUsername()).setIssuedAt(
                        new Date(System.currentTimeMillis())).setExpiration(
                        new Date(System.currentTimeMillis() + 600000)).claim("id", id).claim("phoneNum", phone).claim("roles",
                        roles).claim("email", email).claim("user_id", user_id).signWith(getSigningKey(),
                        SignatureAlgorithm.HS256) //600000=10m
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
