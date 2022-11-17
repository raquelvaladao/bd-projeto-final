package com.scc.campanha.database.models;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DIVULGACAO")
@Builder
public class Divulgacao {

    //todo: não precisa de um embeddedid pra chave única. Mas, como é FK, precisa do ID e MapsId. Se tivesse embedded, bastaria MapsId.

    @Id
    @Column(name = "D_CPF", columnDefinition = "CHAR")
    private String cpf;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "D_CPF", referencedColumnName = "V_CPF", columnDefinition = "CHAR")
    private Voluntario voluntario;

}
