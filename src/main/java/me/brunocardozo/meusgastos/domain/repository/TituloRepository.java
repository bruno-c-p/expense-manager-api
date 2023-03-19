package me.brunocardozo.meusgastos.domain.repository;

import me.brunocardozo.meusgastos.domain.model.Titulo;
import me.brunocardozo.meusgastos.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
    @Query(nativeQuery = true, value = "select * from titulo where data_vencimento between to_timestamp(:periodoInicial, 'DD/MM/YYYY') and to_timestamp(:periodoFinal, 'DD/MM/YYYY')")
    List<Titulo> obterFluxoCaixaPorDataVencimento(@Param("periodoInicial") String periodoInicial, @Param("periodoFinal") String periodoFinal);
    List<Titulo> findByUsuario(Usuario usuario);
}