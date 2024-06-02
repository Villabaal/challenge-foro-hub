package com.aluracursos.foro_hub.infra.security;

import com.aluracursos.foro_hub.domain.author.AuthorRepository;
import com.aluracursos.foro_hub.infra.errores.ErrorJWTExpirationData;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private AuthorRepository repo;
    @Autowired TokenService tokenService;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            var authHeader = Optional.ofNullable(request.getHeader("Authorization"));
            if (authHeader.isPresent()) {
                var token = authHeader.get().replace("Bearer ", "");
                var login = Optional.ofNullable(tokenService.getSubject(token));
                if (login.isPresent()) {
                    var user = repo.findByEmail(login.get()).orElseThrow();
                    var auth = new UsernamePasswordAuthenticationToken(user, null,
                            user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(request,response);
        }
        catch (TokenExpiredException e){
            var errorDto = new ErrorJWTExpirationData( e );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write( mapper.writeValueAsString( errorDto ) );

        }
    }
}