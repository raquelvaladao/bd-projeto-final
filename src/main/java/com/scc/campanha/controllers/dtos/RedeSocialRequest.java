package com.scc.campanha.controllers.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RedeSocialRequest {
    private String nomeUsuario;
    private String nomeRedeSocial;
}
