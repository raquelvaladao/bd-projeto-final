package com.scc.campanha.database.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
 *           A consulta de SELECT BUSCAR_CONTEUDO_MAIS_CLIQUES_POR_PERIODO, em repositories/ConsultasSQL
 * */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoColunasSelectResponse {
    private String titulo;

    private String publicoAlvo;

    private Float mediaDeCliques;
}
