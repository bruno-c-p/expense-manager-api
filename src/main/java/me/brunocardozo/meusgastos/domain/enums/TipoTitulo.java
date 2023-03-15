package me.brunocardozo.meusgastos.domain.enums;

public enum TipoTitulo {
    A_RECEBER("A receber"),
    A_PAGAR("A pagar");

    private String valor;

    private TipoTitulo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }
}
