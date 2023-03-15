package me.brunocardozo.meusgastos.domain.service;

import me.brunocardozo.meusgastos.domain.exception.ResourceNotFoundException;
import me.brunocardozo.meusgastos.domain.model.Usuario;
import me.brunocardozo.meusgastos.domain.repository.UsuarioRepository;
import me.brunocardozo.meusgastos.dto.usuario.UsuarioRequestDTO;
import me.brunocardozo.meusgastos.dto.usuario.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UsuarioService implements ICRUDService<UsuarioRequestDTO, UsuarioResponseDTO> {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<UsuarioResponseDTO> obterTodos() {
        List<Usuario> usuarios = repository.findAll();
        return usuarios.stream().map(usuario -> mapper.map(usuario, UsuarioResponseDTO.class)).toList();
    }
    @Override
    public UsuarioResponseDTO obterPorId(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.map(value -> mapper.map(value, UsuarioResponseDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }
    @Override
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        validarUsuario(dto);
        Usuario usuario = mapper.map(dto, Usuario.class);
        usuario.setId(null);
        usuario = repository.save(usuario);
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }
    @Override
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuarioBanco = obterPorId(id);
        validarUsuario(dto);
        Usuario usuario = mapper.map(dto, Usuario.class);
        usuario.setId(id);
        usuario.setDataInativacao(usuarioBanco.getDataInativacao());
        usuario = repository.save(usuario);
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }
    @Override
    public void excluir(Long id) {
        UsuarioResponseDTO usuarioEncontrado = obterPorId(id);
        Usuario usuario = mapper.map(usuarioEncontrado, Usuario.class);
        usuario.setDataInativacao(new Date());
        repository.save(usuario);
    }

    private void validarUsuario(UsuarioRequestDTO dto) {
        if (dto.getEmail() == null || dto.getSenha() == null) {
            throw new ResourceNotFoundException("Email e senha são obrigatórios");
        }
    }
}
