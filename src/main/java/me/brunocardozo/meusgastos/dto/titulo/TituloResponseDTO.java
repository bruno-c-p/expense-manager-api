package me.brunocardozo.meusgastos.dto.titulo;

import me.brunocardozo.meusgastos.domain.enums.TipoTitulo;
import me.brunocardozo.meusgastos.dto.centrodecusto.CentroDeCustoResponseDTO;

import java.util.Date;
import java.util.List;

public class TituloResponseDTO {
    private Long id;
    private String nome;
    private TipoTitulo tipo;
    private List<CentroDeCustoResponseDTO> centrosDeCusto;
    private Double valor;
    private Date dataCadastro;
    private Date dataReferencia;
    private Date dataVencimento;
    private Date dataPagamento;
    private String observacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoTitulo getTipo() {
        return tipo;
    }

    public void setTipo(TipoTitulo tipo) {
        this.tipo = tipo;
    }

    public List<CentroDeCustoResponseDTO> getCentrosDeCusto() {
        return centrosDeCusto;
    }

    public void setCentrosDeCusto(List<CentroDeCustoResponseDTO> centrosDeCusto) {
        this.centrosDeCusto = centrosDeCusto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(Date dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
