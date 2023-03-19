package me.brunocardozo.meusgastos.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.brunocardozo.meusgastos.common.ConversorData;
import me.brunocardozo.meusgastos.domain.model.ErroResposta;
import me.brunocardozo.meusgastos.domain.model.Usuario;
import me.brunocardozo.meusgastos.dto.usuario.LoginRequestDTO;
import me.brunocardozo.meusgastos.dto.usuario.LoginResponseDTO;
import me.brunocardozo.meusgastos.dto.usuario.UsuarioResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super();
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/auth");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginRequestDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
            return authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage());
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        Usuario usuario = (Usuario) authResult.getPrincipal();
        String token = jwtUtil.gerarToken(authResult);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(createUsuarioResponseDTO(usuario), "Bearer " + token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(loginResponseDTO));
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        String dataHora = ConversorData.converterDateParaString(new Date());
        ErroResposta erro = new ErroResposta(dataHora, 401, "Unauthorized", failed.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(erro));
    }

    private UsuarioResponseDTO createUsuarioResponseDTO(Usuario usuario) {
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setId(usuario.getId());
        usuarioResponseDTO.setNome(usuario.getNome());
        usuarioResponseDTO.setEmail(usuario.getEmail());
        usuarioResponseDTO.setFoto(usuario.getFoto());
        usuarioResponseDTO.setDataCadastro(usuario.getDataCadastro());
        usuarioResponseDTO.setDataInativacao(usuario.getDataInativacao());
        return usuarioResponseDTO;
    }
}
