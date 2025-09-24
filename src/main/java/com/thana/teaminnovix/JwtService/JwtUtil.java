package com.thana.teaminnovix.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    public static final String SECERET_KEY_STRING = "wsu4Xmjfm9JbTJHroeZn6Ae8AEilYfKI";

    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECERET_KEY_STRING.getBytes());

    public String generateToken(UserDetails userDetails) throws Exception {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(secretKey, SignatureAlgorithm.forName("HS256"))
                .compact();
    }
}
