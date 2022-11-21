package com.scc.campanha.database.models.pks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


/*
 *       - As anotações @NoArgsConstructor, @AllArgsConstructor, @Data e @Builder são bibliotecas java geradoras
 *       de boilerplate para getters/setters/construtores/equals/hashcode/To string/padrão de builder
 *       do POO pra criação de um objeto.
 *
 *       - As anotações @Table, @Column, @Id e @Entity são para espelhar o esquema do banco e mapear os devidos campos
 *
 *       - A anotação @ManyToOne é para mapear alguma FK em caso de JOIN na consulta SQL. O mesmo ocorre para
 *       @OneToMany e @ManyToMany, mas estas 2 últimas anotações não foram utilizadas (essas anotações ajudam em alto
 *       nível com ocultação de alguma tabela intermediária do esquema, mas quisemos manter os objetos em java o mais
 *       próximo possível do esquema).
 *
 *      - A anotação @Embeddable mostra que os objetos contidos nesta classe estão contidos na outra
 *      que contém esta como atributo. Como o JPA não entende o mapeamento de chaves compostas
 *      enquanto uma mera "lista de atributos", tivemos que juntar essa lista de atributos numa
 *      classe PK.
 * */
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PKPerfilRedeSocial implements Serializable {

    @Column(name = "PRS_NOME_USUARIO")
    private String nomeUsuario;

    @Column(name = "PRS_REDE_SOCIAL")
    private String redeSocial;
}
