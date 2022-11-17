package com.scc.campanha.database.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "VEICULO")
@Data
public class Veiculo {

    @Id
    @Column(name = "V_PLACA", columnDefinition = "CHAR")
    private String placa;

    @ManyToOne
    @JoinColumn(name = "V_MOTORISTA", columnDefinition = "CHAR")
    private Motorista dono;

    @Column(name = "V_QTDE_MAX_CAIXAS")
    private Integer capacidade;
}
