package com.boy.pjtn.hello.utils;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.boy.pjtn.hello.services.UserDetailService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {


  private JwtUtil jwtUtil;
  private UserDetailService userDetailService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userId;

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      try {
        filterChain.doFilter(request, response);
      } catch (java.io.IOException | ServletException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return;
    }

    jwt = authHeader.substring(7); // Remove "Bearer "
    userId = jwtUtil.extractUsername(jwt);

    log.info(jwt);
    log.info(userId);


    if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailService.loadUserByUsername(userId);


      if (jwtUtil.isTokenValid(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    try {
      filterChain.doFilter(request, response);
    } catch (java.io.IOException | ServletException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      log.warn(e.getMessage());
    }
  }
}
