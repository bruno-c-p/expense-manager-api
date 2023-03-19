package me.brunocardozo.meusgastos.dto.dashboard;

import me.brunocardozo.meusgastos.dto.titulo.TituloResponseDTO;

import java.util.List;

public class DashboardResponseDTO {
    private Double totalParaPagar;
    private Double totalParaReceber;
    private Double saldo;
    private List<TituloResponseDTO> titulosParaPagar;
    private List<TituloResponseDTO> titulosParaReceber;

    public DashboardResponseDTO() {}

    public DashboardResponseDTO(Double totalParaPagar, Double totalParaReceber, Double saldo, List<TituloResponseDTO> titulosParaPagar, List<TituloResponseDTO> titulosParaReceber) {
        this.totalParaPagar = totalParaPagar;
        this.totalParaReceber = totalParaReceber;
        this.saldo = saldo;
        this.titulosParaPagar = titulosParaPagar;
        this.titulosParaReceber = titulosParaReceber;
    }

    public Double getTotalParaPagar() {
        return totalParaPagar;
    }

    public void setTotalParaPagar(Double totalParaPagar) {
        this.totalParaPagar = totalParaPagar;
    }

    public Double getTotalParaReceber() {
        return totalParaReceber;
    }

    public void setTotalParaReceber(Double totalParaReceber) {
        this.totalParaReceber = totalParaReceber;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public List<TituloResponseDTO> getTitulosParaPagar() {
        return titulosParaPagar;
    }

    public void setTitulosParaPagar(List<TituloResponseDTO> titulosParaPagar) {
        this.titulosParaPagar = titulosParaPagar;
    }

    public List<TituloResponseDTO> getTitulosParaReceber() {
        return titulosParaReceber;
    }

    public void setTitulosParaReceber(List<TituloResponseDTO> titulosParaReceber) {
        this.titulosParaReceber = titulosParaReceber;
    }
}
