package me.brunocardozo.meusgastos.dto.usuario;

public class LoginResponseDTO {
    private String token;
    private UsuarioResponseDTO usuario;

    public LoginResponseDTO(UsuarioResponseDTO usuarioResponseDTO, String token) {
        this.usuario = usuarioResponseDTO;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }
}
