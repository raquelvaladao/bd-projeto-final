package com.scc.campanha.enums;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum TipoVoluntario {

    MOT("Motorista"),
    DIV("Ddivulgação"),
    INF("Infraestrutura"),
    COO("Coordenação"),
    ORG("Organização");

    private final String nome;

}
