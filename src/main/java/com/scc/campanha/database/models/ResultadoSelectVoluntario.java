package com.scc.campanha.database.models;


import lombok.Builder;


/*
*       A anotação @Builder abaixo é um código abstraído em java e serve apenas para melhor
*       legibilidade do código. Ele substitui o instanciamento do objeto com "new Objeto()"
*       e muitos setters, para uma sintaxe melhor do tipo Objeto.builder.build().
* */
@Builder
public class ResultadoSelectVoluntario {

    private String nome;
    private String cpf;
    private String email;
    private String tipo;

    public ResultadoSelectVoluntario(String nome, String cpf, String email, String tipo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.tipo = tipo;
    }

    public ResultadoSelectVoluntario() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }
}
