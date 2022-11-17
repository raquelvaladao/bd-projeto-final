package com.scc.campanha.database.models.pks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

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
