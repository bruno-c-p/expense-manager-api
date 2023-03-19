package me.brunocardozo.meusgastos.domain.service;

import me.brunocardozo.meusgastos.domain.exception.ResourceBadRequestException;
import me.brunocardozo.meusgastos.domain.exception.ResourceNotFoundException;
import me.brunocardozo.meusgastos.domain.model.Usuario;
import me.brunocardozo.meusgastos.domain.repository.UsuarioRepository;
import me.brunocardozo.meusgastos.dto.usuario.UsuarioRequestDTO;
import me.brunocardozo.meusgastos.dto.usuario.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
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
        Optional<Usuario> usuarioBanco = repository.findByEmail(dto.getEmail());
        if (usuarioBanco.isPresent()) throw new ResourceBadRequestException("Email já cadastrado");
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
        usuario.setDataCadastro(usuarioBanco.getDataCadastro());
        usuario.setDataInativacao(usuarioBanco.getDataInativacao());
        usuario = repository.save(usuario);
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }
    @Override
    public void excluir(Long id) {
        Optional<Usuario> usuarioBanco = repository.findById(id);
        if (usuarioBanco.isEmpty()) throw new ResourceNotFoundException("Usuário não encontrado");
        Usuario usuario = usuarioBanco.get();
        usuario.setDataInativacao(new Date());
        repository.save(usuario);
    }

    public UsuarioResponseDTO obterPorEmail(String email) {
        Optional<Usuario> usuario = repository.findByEmail(email);
        return usuario.map(value -> mapper.map(value, UsuarioResponseDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    private void validarUsuario(UsuarioRequestDTO dto) {
        if (dto.getEmail() == null || dto.getSenha() == null) {
            throw new ResourceNotFoundException("Email e senha são obrigatórios");
        }
    }
}
