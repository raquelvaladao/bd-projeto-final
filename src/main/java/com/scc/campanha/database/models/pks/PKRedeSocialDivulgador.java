package com.scc.campanha.database.models.pks;

import com.scc.campanha.database.models.Divulgacao;
import com.scc.campanha.database.models.PerfilRedeSocial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PKRedeSocialDivulgador implements Serializable {

    @MapsId
    @JoinColumn(name = "RSD_DIVULGADOR_CPF", referencedColumnName = "D_CPF", columnDefinition = "CHAR")
    @ManyToOne(fetch = FetchType.LAZY)
    private Divulgacao fkDivulgacao;

    @MapsId
    @JoinColumns({
            @JoinColumn(name = "RSD_REDE_SOCIAL_NOME_USUARIO", referencedColumnName = "PRS_NOME_USUARIO"),
            @JoinColumn(name = "RSD_REDE_SOCIAL_REDE_SOCIAL", referencedColumnName = "PRS_REDE_SOCIAL")
    })
    @ManyToOne(fetch = FetchType.LAZY)
    private PerfilRedeSocial fkPerfilRedeSocial;
}
