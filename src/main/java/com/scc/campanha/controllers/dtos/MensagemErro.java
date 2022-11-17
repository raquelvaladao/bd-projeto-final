package com.scc.campanha.controllers.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MensagemErro {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy HH:mm:ss")
    private Date dataErro;

    private List<String> campos;

    private String violacao;

    private String mensagem;
}
