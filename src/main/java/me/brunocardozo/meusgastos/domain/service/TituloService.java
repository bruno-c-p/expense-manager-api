package me.brunocardozo.meusgastos.domain.service;

import me.brunocardozo.meusgastos.domain.exception.ResourceBadRequestException;
import me.brunocardozo.meusgastos.domain.exception.ResourceNotFoundException;
import me.brunocardozo.meusgastos.domain.model.Titulo;
import me.brunocardozo.meusgastos.domain.model.Usuario;
import me.brunocardozo.meusgastos.domain.repository.TituloRepository;
import me.brunocardozo.meusgastos.dto.titulo.TituloRequestDTO;
import me.brunocardozo.meusgastos.dto.titulo.TituloResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TituloService implements ICRUDService<TituloRequestDTO, TituloResponseDTO> {
    @Autowired
    private TituloRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<TituloResponseDTO> obterTodos() {
        List<Titulo> lista = repository.findByUsuario(obterUsuarioLogado());
        return lista.stream().map(titulo -> mapper.map(titulo, TituloResponseDTO.class)).toList();
    }
    @Override
    public TituloResponseDTO obterPorId(Long id) {
        Optional<Titulo> titulo = repository.findById(id);
        if (titulo.isEmpty() || titulo.get().getUsuario().getId() != obterUsuarioLogado().getId()) {
            throw new ResourceNotFoundException("Título não encontrado");
        }
        return mapper.map(titulo.get(), TituloResponseDTO.class);
    }
    @Override
    public TituloResponseDTO cadastrar(TituloRequestDTO dto) {
        validarTitulo(dto);
        Titulo titulo = mapper.map(dto, Titulo.class);
        titulo.setUsuario(obterUsuarioLogado());
        titulo.setId(null);
        titulo.setDataCadastro(new Date());
        titulo = repository.save(titulo);
        return mapper.map(titulo, TituloResponseDTO.class);
    }
    @Override
    public TituloResponseDTO atualizar(Long id, TituloRequestDTO dto) {
        obterPorId(id);
        validarTitulo(dto);
        Titulo titulo = mapper.map(dto, Titulo.class);
        titulo.setId(id);
        titulo.setUsuario(obterUsuarioLogado());
        titulo = repository.save(titulo);
        return mapper.map(titulo, TituloResponseDTO.class);
    }
    @Override
    public void excluir(Long id) {
        obterPorId(id);
        repository.deleteById(id);
    }

    public List<TituloResponseDTO> obterPorDataVencimento(String periodoInicial, String periodoFinal) {
        List<Titulo> lista = repository.obterFluxoCaixaPorDataVencimento(periodoInicial, periodoFinal);
        return lista.stream().map(titulo -> mapper.map(titulo, TituloResponseDTO.class)).toList();
    }

    private void validarTitulo(TituloRequestDTO dto) {
        if (dto.getTipo() == null) throw new ResourceBadRequestException("Tipo do título não informado");
        if (dto.getValor() == null) throw new ResourceBadRequestException("Valor do título não informado");
        if (dto.getDataVencimento() == null) throw new ResourceBadRequestException("Data de vencimento do título não informado");
    }

    private Usuario obterUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
