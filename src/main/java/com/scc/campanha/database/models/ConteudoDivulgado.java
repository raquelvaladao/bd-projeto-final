package com.scc.campanha.database.models;

import com.scc.campanha.database.models.pks.PKConteudoDivulgado;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Entity
@Table(name = "CONTEUDO_DIVULGADO")
public class ConteudoDivulgado {

    @EmbeddedId
    private PKConteudoDivulgado pkConteudoDivulgado;

    @Column(name = "CD_TEXTO_DIVULGACAO")
    private String textoDivulgacao;

    @Column(name = "CD_PUBLICO")
    private BigInteger publico;
}
