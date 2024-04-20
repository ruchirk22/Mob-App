package com.mobicom.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtHelper jwtHelper;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    String requestHeader = request.getHeader("Authorization");
    String username = null;
    String token = null;
    if (requestHeader != null && requestHeader.startsWith("Bearer")) {
      token = requestHeader.substring(7);
      try {
        username = this.jwtHelper.getUsernameFromToken(token);
      } catch (IllegalArgumentException e) {
        e.getMessage();
      } catch (ExpiredJwtException e) {
        e.getMessage();
      } catch (MalformedJwtException e) {
        e.getMessage();
      } catch (Exception e) {
        e.getMessage();
      }
    } else {
      System.err.println("Invalid header value!");
    }

    if (
      username != null &&
      SecurityContextHolder.getContext().getAuthentication() == null
    ) {
      UserDetails userDetails =
        this.userDetailsService.loadUserByUsername(username);
      Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
      if (validateToken) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities()
        );
        authentication.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } else {
        System.err.println("Validation failed!");
      }
    }

    filterChain.doFilter(request, response);
  }
}
