package com.scc.campanha.controllers.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/*
 *       Essa classe serve para ser enviada como resposta para o usuário caso haja alguma exception de banco.
 *       Envia data atual, os campos de erro caso hajam, mensagem amigável, caso haja, e as violações
 *       mapeadas, caso hajam.
 *
 *       A anotação @Builder abaixo é um código abstraído em java e serve apenas para melhor
 *       legibilidade do código. Ele substitui o instanciamento do objeto com "new Objeto()"
 *       e muitos setters, para uma sintaxe melhor do tipo Objeto.builder.build().
 *
 *       A anotação @JsonInclude(JsonInclude.Include.NON_NULL) abaixo é um código abstraído em java
 *       para ser mostrado na interface apenas com os campos que não estão nulos.
 * */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MensagemErro {

    private Date dataErro;
    private List<String> campos;
    private String violacao;
    private String mensagem;

    public MensagemErro(Date dataErro, List<String> campos, String violacao, String mensagem) {
        this.dataErro = dataErro;
        this.campos = campos;
        this.violacao = violacao;
        this.mensagem = mensagem;
    }

    public MensagemErro() {
    }

    public Date getDataErro() {
        return dataErro;
    }

    public void setDataErro(Date dataErro) {
        this.dataErro = dataErro;
    }

    public List<String> getCampos() {
        return campos;
    }

    public void setCampos(List<String> campos) {
        this.campos = campos;
    }

    public String getViolacao() {
        return violacao;
    }

    public void setViolacao(String violacao) {
        this.violacao = violacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
