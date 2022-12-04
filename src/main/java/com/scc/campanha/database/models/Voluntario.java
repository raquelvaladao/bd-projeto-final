package com.scc.campanha.database.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 *       - As anotações @NoArgsConstructor, @AllArgsConstructor, @Data e @Builder são bibliotecas java geradoras
 *       de boilerplate para getters/setters/construtores/equals/hashcode/To string/padrão de builder
 *       do POO pra criação de um objeto.
 *
 *       - As anotações @Table, //@Column, @Id, @MapsId e @Entity são para espelhar o esquema do banco
 *       e mapear os devidos campos ou nomes.
 *              - O atributo columnDefinition de //@Column mapeia tipos específicos do Oracle, como CHAR ou TIMESTAMP,
 *              que não são reocnhecidos naturalmente pelo JPA.
 *
 *       - A anotação @ManyToOne é para mapear alguma FK em caso de JOIN na consulta SQL. O mesmo ocorre para
 *       @OneToMany e @ManyToMany, mas estas 2 últimas anotações não foram utilizadas (essas anotações ajudam em alto
 *       nível com ocultação de alguma tabela intermediária do esquema, mas quisemos manter os objetos em java o mais
 *       próximo possível do esquema).
 * */
/*@Entity
@Builder
@Table(name = "VOLUNTARIO")*/
public class Voluntario {

    //@Id
    //@Column(name = "V_CPF", columnDefinition = "CHAR")
    private String cpf;

    //@Column(name = "V_NOME")
    private String nome;

    //@Column(name = "V_EMAIL")
    private String email;

    //@Column(name = "V_TIPO_VOLUNTARIO", columnDefinition = "CHAR")
    private String tipoVoluntario;

    //@Column(name = "V_CIDADE_ENDERECO")
    private String cidade;

    //@Column(name = "V_RUA_ENDERECO")
    private String rua;

    //@Column(name = "V_BAIRRO_ENDERECO")
    private String bairro;

    //@Column(name = "V_NUMERO_ENDERECO", columnDefinition = "CHAR")
    private String numero;

    //@Column(name = "V_CEP_ENDERECO", columnDefinition = "CHAR")
    private String cep;

    public Voluntario() {
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTipoVoluntario() {
        return tipoVoluntario;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    //todo: apagar essa classe
}
