package me.brunocardozo.meusgastos.domain.model;

public class ErroResposta {
    private String data;
    private Integer status;
    private String titulo;
    private String mensagem;

    public ErroResposta(String data, Integer status, String titulo, String mensagem) {
        this.data = data;
        this.status = status;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
