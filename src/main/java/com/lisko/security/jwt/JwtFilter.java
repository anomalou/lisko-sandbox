package com.lisko.security.jwt;

import com.lisko.exception.AuthorizationException;
import com.lisko.security.jwt.entity.JwtEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/1/24
 */
@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final String BAERER_PREFIX = "Baerer ";
    private final String HEADER_NAME = "Authorization";

    private final JwtUtil util;
    private final UserDetailsService detailsService;

    public JwtFilter (JwtUtil util, UserDetailsService detailsService) {
        this.util = util;
        this.detailsService = detailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HEADER_NAME);

        try{
            if(StringUtils.isBlank(header) || !StringUtils.startsWith(header, BAERER_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = header.substring(BAERER_PREFIX.length());

            JwtEntity jwt = util.parse(token);

            if (StringUtils.isBlank(jwt.getBody().getUsername())) {
                throw new AuthorizationException();
            }

            UserDetails details = detailsService.loadUserByUsername(jwt.getBody().getUsername());

            if (!util.validate(token)) {
                throw new AuthorizationException();
            }

            SecurityContext context = SecurityContextHolder.createEmptyContext();

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            details,
                            null,
                            details.getAuthorities()
                    );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);

            filterChain.doFilter(request, response);
        } catch (Exception e){
            log.error("Authorization failed. Exception: {}", e.getMessage());
            throw e;
        }
    }
}
