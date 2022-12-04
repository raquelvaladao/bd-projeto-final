package com.scc.campanha.database.models;


/*
*   Classe recebida pelo usuário na interface, em forma de JSON, para ter seus atributos salvos no banco.
* */
public class InsercaoVoluntario {

    private String cpf;
    private String nome;
    private String email;
    private String tipoVoluntario;
    private String cidade;
    private String rua;
    private String bairro;
    private String numero;
    private String cep;

    public InsercaoVoluntario(String cpf, String nome, String email, String tipoVoluntario, String cidade, String rua, String bairro, String numero, String cep) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.tipoVoluntario = tipoVoluntario;
        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoVoluntario() {
        return tipoVoluntario;
    }

    public void setTipoVoluntario(String tipoVoluntario) {
        this.tipoVoluntario = tipoVoluntario;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
