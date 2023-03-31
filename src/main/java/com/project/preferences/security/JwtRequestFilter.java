package com.project.preferences.security;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeaderValue = httpServletRequest.getHeader(AUTHORIZATION);
        String username;
        String jwtToken;
        //парсим токен
        if (tokenHeaderValue != null && tokenHeaderValue.startsWith("Bearer ")) {
            jwtToken = tokenHeaderValue.substring(7);
            username = jwtTokenService.getUsernameFromToken(jwtToken);
            val context = SecurityContextHolder.getContext();
            if (username != null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                val usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                context.setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        //передаем запрос дальше по цепочке фильтров
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}