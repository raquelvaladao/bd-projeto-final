package com.scc.campanha.database.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MOTORISTA")
@Data
public class Motorista implements Serializable {

    @Id
    @Column(name = "M_CPF", columnDefinition = "CHAR")
    private String cpf;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "M_CPF", referencedColumnName = "V_CPF", columnDefinition = "CHAR")
    private Voluntario voluntario;
}
