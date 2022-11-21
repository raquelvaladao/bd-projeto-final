package com.scc.campanha.database.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


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
