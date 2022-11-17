package com.scc.campanha.database.models;


import com.scc.campanha.database.models.pks.PKPerfilRedeSocial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "PERFIL_REDE_SOCIAL")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilRedeSocial implements Serializable {

    @EmbeddedId
    private PKPerfilRedeSocial pkPerfilRedeSocial;

}
