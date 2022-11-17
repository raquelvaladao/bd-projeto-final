package com.scc.campanha.database.models;


import com.scc.campanha.database.models.pks.PKRedeSocialDivulgador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "REDE_SOCIAL_DIVULGADOR")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedeSocialDivulgador {

    //PK composta com FK
    @EmbeddedId
    private PKRedeSocialDivulgador pkRedeSocialDivulgador;

    @Column(name = "RSD_HORAS_VOLUNTARIADO")
    private Integer horasVoluntariado;

}
