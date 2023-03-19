package me.brunocardozo.meusgastos.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.brunocardozo.meusgastos.domain.model.Usuario;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private JwtUtil jwtUtil;
    private UserDetailsSecurityServer userDetailsSecurityServer;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsSecurityServer userDetailsSecurityServer) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsSecurityServer = userDetailsSecurityServer;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (isValidHeader(header)) {
            UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
            if (auth != null && auth.isAuthenticated()) SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtUtil.isValidToken(token)) {
            String email = jwtUtil.getUsername(token);
            Usuario usuario = (Usuario) userDetailsSecurityServer.loadUserByUsername(email);
            return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        }
        return null;
    }

    private boolean isValidHeader(String header) {
        return header != null && header.startsWith("Bearer ");
    }
}
