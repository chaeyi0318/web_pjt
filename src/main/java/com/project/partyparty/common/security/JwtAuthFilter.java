package com.project.partyparty.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.partyparty.common.exception.Message;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = jwtUtil.resolveToken(request);
        String refreshToken = jwtUtil.resolveRefreshToken(request);

        log.warn("URL : " + request.getRequestURL() + ", METHOD : " + request.getMethod());

        if (accessToken != null) {
            try {
                if (jwtUtil.validateToken(accessToken)) {
                    Claims info = jwtUtil.getUserInfoFromToken(accessToken);
                    setAuthentication(info.getSubject());
                } else if (refreshToken != null) {
                    RefreshToken refreshTokenEntity = refreshTokenService.findByToken(refreshToken)
                            .orElseThrow(() -> new RuntimeException("Refresh token not found in database"));
                    refreshTokenService.verifyExpiration(refreshTokenEntity);
                    String username = refreshTokenEntity.getUsername();
                    String newAccessToken = jwtUtil.generateAccessToken(username);
                    response.setHeader("New-Access-Token", newAccessToken);
                    setAuthentication(username);
                } else {
                    jwtExceptionHandler(response, "토큰이 만료되었거나 잘못된 토큰입니다.", HttpStatus.UNAUTHORIZED.value());
                    return;
                }
            } catch (Exception ex) {
                jwtExceptionHandler(response, ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtUtil.createAuthentication(username);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    public void jwtExceptionHandler(HttpServletResponse response, String msg, int statusCode) {
        response.setStatus(statusCode);
        response.setContentType("application/json; charset=utf-8");
        try {
            String json = new ObjectMapper().writeValueAsString(new Message(false, msg, null));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}