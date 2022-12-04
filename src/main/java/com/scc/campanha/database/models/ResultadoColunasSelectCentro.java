package com.scc.campanha.database.models;


import lombok.Builder;


/*
 *       A anotação @Builder abaixo é um código abstraído em java e serve apenas para melhor
 *       legibilidade do código. Ele substitui o instanciamento do objeto com "new Objeto()"
 *       e muitos setters, para uma sintaxe melhor do tipo Objeto.builder.build().
 */
@Builder
public class ResultadoColunasSelectCentro {

    private String rua;
    private String bairro;
    private String numero;
    private Integer totalTriagens;
    private Integer totalArrecadacoes;
    private String tipo;


    public ResultadoColunasSelectCentro(String rua, String bairro, String numero, Integer totalTriagens, Integer totalArrecadacoes, String tipo) {
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.totalTriagens = totalTriagens;
        this.totalArrecadacoes = totalArrecadacoes;
        this.tipo = tipo;
    }

    public ResultadoColunasSelectCentro() {
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getTotalTriagens() {
        return totalTriagens;
    }

    public void setTotalTriagens(Integer totalTriagens) {
        this.totalTriagens = totalTriagens;
    }

    public Integer getTotalArrecadacoes() {
        return totalArrecadacoes;
    }

    public void setTotalArrecadacoes(Integer totalArrecadacoes) {
        this.totalArrecadacoes = totalArrecadacoes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
