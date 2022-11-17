package com.scc.campanha.controllers.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class PublicacaoRequest {

    //FK 1
    private String usuarioRedeSocial;

    private String redeSocial;

    //FK 2
    private String tituloDoConteudoDivulgado;

    private String anoDoConteudoDivulgado;

    private String alcance;

    private String data; //todo: tratar erros data

    private String totalCliques;

    private String impressoes;

}
