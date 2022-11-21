package com.scc.campanha.database.models;


import lombok.Data;

import javax.persistence.*;


/*
 *       - As anotações @NoArgsConstructor, @AllArgsConstructor, @Data e @Builder são bibliotecas java geradoras
 *       de boilerplate para getters/setters/construtores/equals/hashcode/To string/padrão de builder
 *       do POO pra criação de um objeto.
 *
 *       - As anotações @Table, @Column, @Id, @MapsId e @Entity são para espelhar o esquema do banco
 *       e mapear os devidos campos ou nomes.
 *              - O atributo columnDefinition de @Column mapeia tipos específicos do Oracle, como CHAR ou TIMESTAMP,
 *              que não são reocnhecidos naturalmente pelo JPA.
 *
 *       - A anotação @JoinColumns é para espelhar FKs
 *
 *       - A anotação @ManyToOne é para mapear alguma FK em caso de JOIN na consulta SQL. O mesmo ocorre para
 *       @OneToMany e @ManyToMany, mas estas 2 últimas anotações não foram utilizadas (essas anotações ajudam em alto
 *       nível com ocultação de alguma tabela intermediária do esquema, mas quisemos manter os objetos em java o mais
 *       próximo possível do esquema).
 * */
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

    //mapeando FK de PerfilRedeSocial
    @ManyToOne(fetch = FetchType.LAZY) //todo: todo
    @JoinColumns({
            @JoinColumn(name = "P_REDE_SOCIAL_NOME_USUARIO", referencedColumnName = "PRS_NOME_USUARIO"),
            @JoinColumn(name = "P_REDE_SOCIAL_REDE_SOCIAL", referencedColumnName = "PRS_REDE_SOCIAL"),
    })
    private PerfilRedeSocial perfilRedeSocial;

    //mapeando FK de ConteudoDivulgado
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
