package me.brunocardozo.meusgastos.domain.repository;

import me.brunocardozo.meusgastos.domain.model.CentroDeCusto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long> {}