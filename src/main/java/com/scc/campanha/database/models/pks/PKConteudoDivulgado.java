package com.scc.campanha.database.models.pks;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class PKConteudoDivulgado implements Serializable {

    @Column(name = "CD_TITULO")
    private String titulo;

    //todo: CD_ANO number(4)
    @Column(name = "CD_ANO")
    private String ano;
}
