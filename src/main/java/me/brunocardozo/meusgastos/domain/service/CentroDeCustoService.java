package me.brunocardozo.meusgastos.domain.service;

import me.brunocardozo.meusgastos.domain.exception.ResourceNotFoundException;
import me.brunocardozo.meusgastos.domain.model.CentroDeCusto;
import me.brunocardozo.meusgastos.domain.model.Usuario;
import me.brunocardozo.meusgastos.domain.repository.CentroDeCustoRepository;
import me.brunocardozo.meusgastos.dto.centrodecusto.CentroDeCustoRequestDTO;
import me.brunocardozo.meusgastos.dto.centrodecusto.CentroDeCustoResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CentroDeCustoService implements ICRUDService<CentroDeCustoRequestDTO, CentroDeCustoResponseDTO> {
    @Autowired
    private CentroDeCustoRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<CentroDeCustoResponseDTO> obterTodos() {
        List<CentroDeCusto> lista = repository.findByUsuario(obterUsuarioLogado());
        return lista.stream().map(centroDeCusto -> mapper.map(centroDeCusto, CentroDeCustoResponseDTO.class)).toList();
    }
    @Override
    public CentroDeCustoResponseDTO obterPorId(Long id) {
        Optional<CentroDeCusto> centroDeCusto = repository.findById(id);
        if (centroDeCusto.isEmpty() || centroDeCusto.get().getUsuario().getId() != obterUsuarioLogado().getId()) {
            throw new ResourceNotFoundException("Centro de custo não encontrado");
        }
        return mapper.map(centroDeCusto.get(), CentroDeCustoResponseDTO.class);
    }
    @Override
    public CentroDeCustoResponseDTO cadastrar(CentroDeCustoRequestDTO dto) {
        CentroDeCusto centroDeCusto = mapper.map(dto, CentroDeCusto.class);
        centroDeCusto.setUsuario(obterUsuarioLogado());
        centroDeCusto.setId(null);
        centroDeCusto = repository.save(centroDeCusto);
        return mapper.map(centroDeCusto, CentroDeCustoResponseDTO.class);
    }
    @Override
    public CentroDeCustoResponseDTO atualizar(Long id, CentroDeCustoRequestDTO dto) {
        obterPorId(id);
        CentroDeCusto centroDeCusto = mapper.map(dto, CentroDeCusto.class);
        centroDeCusto.setId(id);
        centroDeCusto.setUsuario(obterUsuarioLogado());
        centroDeCusto = repository.save(centroDeCusto);
        return mapper.map(centroDeCusto, CentroDeCustoResponseDTO.class);
    }
    @Override
    public void excluir(Long id) {
        obterPorId(id);
        repository.deleteById(id);
    }

    private Usuario obterUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
