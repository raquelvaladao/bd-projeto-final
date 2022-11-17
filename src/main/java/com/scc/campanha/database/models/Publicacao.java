package com.scc.campanha.database.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(
        name = "PUBLICACAO",
        //todo: deixar aqui ou
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"P_REDE_SOCIAL_NOME_USUARIO", "P_REDE_SOCIAL_REDE_SOCIAL",
                        "P_CONTEUDO_DIVULGADO_TITULO", "P_CONTEUDO_DIVULGADO_ANO", "P_DATA_HORA"
                })
)
public class Publicacao {

    @Id //todo: gerado a partir de um CREATE SEQUENCE do banco
    @Column(name = "P_ID")
    private Long idSinteticoPublicacao;

    @ManyToOne(fetch = FetchType.LAZY) //todo: todo
    @JoinColumns({
            @JoinColumn(name = "P_REDE_SOCIAL_NOME_USUARIO", referencedColumnName = "PRS_NOME_USUARIO"),
            @JoinColumn(name = "P_REDE_SOCIAL_REDE_SOCIAL", referencedColumnName = "PRS_REDE_SOCIAL"),
    })
    private PerfilRedeSocial perfilRedeSocial;

    @ManyToOne(fetch = FetchType.LAZY) //todo: todo
    @JoinColumns({
            @JoinColumn(name = "P_CONTEUDO_DIVULGADO_TITULO", referencedColumnName = "CD_TITULO"),
            @JoinColumn(name = "P_CONTEUDO_DIVULGADO_ANO", referencedColumnName = "CD_ANO"),
    })
    private ConteudoDivulgado conteudoDivulgado;


    @Column(name = "P_DATA_HORA", columnDefinition = "TIMESTAMP") //todo: date
    private String dataHora;

    @Column(name = "P_ALCANCE")
    private Integer alcance;

    @Column(name = "P_TOTAL_CLIQUES")
    private Long totalCliques;

    @Column(name = "P_IMPRESSOES")
    private Long impressoes;
}
