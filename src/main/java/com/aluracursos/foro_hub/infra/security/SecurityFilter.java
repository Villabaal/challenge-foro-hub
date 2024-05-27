package com.aluracursos.foro_hub.infra.security;

import com.aluracursos.foro_hub.domain.author.AuthorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = Optional.ofNullable( request.getHeader("Authorization") );
        if( authHeader.isPresent() ){
            var token = authHeader.get().replace("Bearer ","");
            var login = Optional.ofNullable( tokenService.getSubject(token) );
            if (login.isPresent()){
                var user = repo.findByEmail( login.get() ).orElseThrow();
                var auth = new UsernamePasswordAuthenticationToken(user,null,
                        user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request,response);
    }
}