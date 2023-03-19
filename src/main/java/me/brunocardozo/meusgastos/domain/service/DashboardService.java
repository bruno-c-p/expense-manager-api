package me.brunocardozo.meusgastos.domain.service;

import me.brunocardozo.meusgastos.domain.enums.TipoTitulo;
import me.brunocardozo.meusgastos.dto.dashboard.DashboardResponseDTO;
import me.brunocardozo.meusgastos.dto.titulo.TituloResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private TituloService tituloService;

    public DashboardResponseDTO obterFluxoDeCaixa(String periodoInicial, String periodoFinal) {
        List<TituloResponseDTO> titulos = tituloService.obterPorDataVencimento(periodoInicial, periodoFinal);
        double totalParaPagar = 0;
        double totalParaReceber = 0;
        double saldo = 0;
        List<TituloResponseDTO> titulosParaPagar = new ArrayList<>();
        List<TituloResponseDTO> titulosParaReceber = new ArrayList<>();
        for (TituloResponseDTO titulo : titulos) {
            if (titulo.getTipo() == TipoTitulo.A_PAGAR) {
                totalParaPagar += titulo.getValor();
                titulosParaPagar.add(titulo);
            } else {
                totalParaReceber += titulo.getValor();
                titulosParaReceber.add(titulo);
            }
        }
        return new DashboardResponseDTO(totalParaPagar, totalParaReceber, saldo, titulosParaPagar, titulosParaReceber);
    }
}
