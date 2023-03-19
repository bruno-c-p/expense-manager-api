package me.brunocardozo.meusgastos.domain.repository;

import me.brunocardozo.meusgastos.domain.model.CentroDeCusto;
import me.brunocardozo.meusgastos.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long> {
    List<CentroDeCusto> findByUsuario(Usuario usuario);
}