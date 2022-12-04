package com.scc.campanha.controllers.dtos;


import com.scc.campanha.database.models.Voluntario;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class BancoDeDadosException extends RuntimeException {

    private String view;
    private Voluntario voluntarioInvalido;
    private String mensagem;

}
