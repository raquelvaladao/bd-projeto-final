package com.scc.campanha.database.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VOLUNTARIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voluntario {

    @Id
    @Column(name = "V_CPF", columnDefinition = "CHAR")
    private String cpf;

    @Column(name = "V_NOME")
    private String nome;

    @Column(name = "V_EMAIL")
    private String email;

    @Column(name = "V_TIPO_VOLUNTARIO", columnDefinition = "CHAR")
    private String tipoVoluntario;

    @Column(name = "V_CIDADE")
    private String cidade;

    @Column(name = "V_RUA")
    private String rua;

    @Column(name = "V_NUMERO", columnDefinition = "CHAR")
    private String numero;

    @Column(name = "V_CEP", columnDefinition = "CHAR")
    private String cep;
}
