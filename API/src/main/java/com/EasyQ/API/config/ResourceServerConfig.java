package com.EasyQ.API.config;

import java.io.IOException;

import com.EasyQ.API.util.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/tenants").hasAuthority("SCOPE_read")
                        .requestMatchers(HttpMethod.POST, "/tenants").hasAuthority("SCOPE_write")
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/actuator/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }

    @Bean
    public OncePerRequestFilter registeredClientFilter(@Value("${client-id:easyq-client}") String registeredClientId, JwtDecoder jwtDecoder) {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                if(TokenUtil.containsBearerToken(authorizationHeader)) {
                    String bearerToken = TokenUtil.extractBearerToken(authorizationHeader);
                    try {
                        String clientId = jwtDecoder.decode(bearerToken)
                                .getClaimAsString("sub");
                        if(!registeredClientId.equals(clientId)) {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getOutputStream().println("{\"error\": \"Unregistered OAuth client.\"}");
                            response.flushBuffer();
                            return;
//                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unregistered OAuth client."); ... custom message is ignored and overriden by tomcat
                        }
                    } catch(BadJwtException e) {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.setContentType("application/json");
                        response.getOutputStream().println("{\"error\": \"Token is malformed or corrupt.\"}");
                        response.flushBuffer();
                        return;
//                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token is malformed or corrupt.");
                    } catch(JwtException e) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType("application/json");
                        response.getOutputStream().println("{\"error\": \"Failed to decode the token.\"}");
                        response.flushBuffer();
                        return;
//                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Failed to decode the token.");
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }
}
