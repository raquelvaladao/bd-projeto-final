package com.scc.campanha.database.models;


import lombok.Builder;

/*
 *       A anotação @Builder abaixo é um código abstraído em java e serve apenas para melhor
 *       legibilidade do código. Ele substitui o instanciamento do objeto com "new Objeto()"
 *       e muitos setters, para uma sintaxe melhor do tipo Objeto.builder.build().
 * */
@Builder
public class ResultadoColunasSelectRoupa {

    private String tipo;
    private String genero;
    private String mes;
    private Integer totalEnviado;


    public ResultadoColunasSelectRoupa(String tipo, String genero, String mes, Integer totalEnviado) {
        this.tipo = tipo;
        this.genero = genero;
        this.mes = mes;
        this.totalEnviado = totalEnviado;
    }

    public ResultadoColunasSelectRoupa() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getTotalEnviado() {
        return totalEnviado;
    }

    public void setTotalEnviado(Integer totalEnviado) {
        this.totalEnviado = totalEnviado;
    }
}
