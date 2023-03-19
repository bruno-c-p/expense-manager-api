package me.brunocardozo.meusgastos.domain.model;

import jakarta.persistence.*;
import me.brunocardozo.meusgastos.domain.enums.TipoTitulo;

import java.util.Date;
import java.util.List;

@Entity
public class Titulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_titulo")
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    private TipoTitulo tipo;
    @ManyToMany
    @JoinTable(
            name = "titulo_centro_de_custo",
            joinColumns = @JoinColumn(name = "id_titulo"),
            inverseJoinColumns = @JoinColumn(name = "id_centro_de_custo"))
    private List<CentroDeCusto> centrosDeCustos;
    @Column(nullable = false)
    private Double valor;
    private Date dataCadastro;
    private Date dataReferencia;
    private Date dataVencimento;
    private Date dataPagamento;
    @Column(columnDefinition = "TEXT")
    private String observacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoTitulo getTipo() {
        return tipo;
    }

    public void setTipo(TipoTitulo tipo) {
        this.tipo = tipo;
    }

    public List<CentroDeCusto> getCentrosDeCustos() {
        return centrosDeCustos;
    }

    public void setCentrosDeCustos(List<CentroDeCusto> centrosDeCustos) {
        this.centrosDeCustos = centrosDeCustos;
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
