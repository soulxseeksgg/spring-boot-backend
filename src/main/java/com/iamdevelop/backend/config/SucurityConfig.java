package com.iamdevelop.backend.config;

import com.iamdevelop.backend.config.token.TokenFilter;
import com.iamdevelop.backend.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SucurityConfig {
    private final String[] PUBLIC = {
            "/actuator/**"
            ,"/user/register"
            ,"/user/login"
            ,"updateUserName"
    };

    private final TokenService tokenService;

    public SucurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

       http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
               .authorizeHttpRequests((requests) -> requests
                       .requestMatchers(PUBLIC).permitAll()
                       .anyRequest().authenticated())
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               //.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
               .addFilterBefore(new TokenFilter(tokenService), UsernamePasswordAuthenticationFilter.class);

       return http.build();
    }

}
