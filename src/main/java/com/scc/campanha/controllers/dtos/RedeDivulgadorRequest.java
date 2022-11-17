package com.scc.campanha.controllers.dtos;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RedeDivulgadorRequest {

    //FK 1 e parte da PK
    private String nomeUsuario;
    private String nomeRedeSocial;

    //FK2 e parte da PK
    private String cpfDivulgacao;

    private Integer horasVoluntariado;
}
