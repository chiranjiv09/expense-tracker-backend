package com.chiranjiv.expense.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.chiranjiv.expense.entity.Users;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtTokenHelper jwtHelper;

    @Autowired
    private CustomUserDetailService customUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractJwtFromRequest(request);

        if (token != null) {
            try {
                String username = jwtHelper.getUsernameFromToken(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    authenticateUser(request, username, token);
                }
            } catch (ExpiredJwtException e) {
                logger.info("JWT token is expired");
            } catch (MalformedJwtException e) {
                logger.info("Invalid JWT token");
            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username");
            } catch (Exception e) {
                logger.error("Error occurred while fetching the username from JWT", e);
            }
        } else {
            logger.info("No valid JWT token found");
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null) {
            logger.debug("Authorization header: " + bearerToken); // Log the Authorization header content
            if (bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            } else {
                logger.info("JWT Token does not begin with Bearer String");
            }
        } else {
            logger.info("Authorization header is missing");
        }
        return null;
    }

    private void authenticateUser(HttpServletRequest request, String username, String token) {
        Users userDetails = customUserDetails.loadUserByUsername(username);
        if (jwtHelper.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            logger.info("JWT Token validation failed");
        }
    }
}
