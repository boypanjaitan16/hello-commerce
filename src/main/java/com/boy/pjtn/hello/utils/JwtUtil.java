package com.boy.pjtn.hello.utils;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
  @Value("${app.jwt.secret}")
  private String jwtSecret;

  @Value("${app.jwt.expiration}")
  private long jwtExpirationMs;

  private Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
  }

  public String generateToken(User user) {
    return Jwts.builder().setSubject(user.getEmail()).claim("id", user.getId().toString())
        .claim("role", user.getRole().getName()).setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
        .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256).compact();
  }

  public String getUserIdFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build()
        .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build()
          .parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }
}

