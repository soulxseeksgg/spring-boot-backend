package com.iamdevelop.backend.config.token;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.iamdevelop.backend.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenFilter extends GenericFilterBean {

    private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String athorization = httpServletRequest.getHeader("Authorization");

        if(ObjectUtils.isEmpty(athorization)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        if(!athorization.startsWith("Bearer")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String token = athorization.substring(7);
        DecodedJWT decodedJWT = tokenService.verify(token);

        if(decodedJWT == null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        String principal = decodedJWT.getClaim("principal").asString();
        String role = decodedJWT.getClaim("role").asString();

        List<GrantedAuthority> athorities = new ArrayList<>();
        athorities.add(new SimpleGrantedAuthority(role));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal,"protected",athorities);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
