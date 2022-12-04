package com.scc.campanha.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;



public enum TipoVoluntario {

    MOT("Motorista"),
    DIV("Divulgação"),
    INF("Infraestrutura"),
    COO("Coordenação"),
    ORG("Organização");

    private final String nome;

    TipoVoluntario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
