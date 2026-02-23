package com.ecommerce.auth.infrastructure.security.jwt;

import com.ecommerce.auth.application.port.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService implements TokenProvider {

  private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private static final long EXPIRATION_MS = 1_000 * 60 * 60; // 1 hour

  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
        .signWith(KEY)
        .compact();
  }

}
