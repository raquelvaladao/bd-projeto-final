package com.scc.campanha.database.repositories;

import com.scc.campanha.database.ConsultasSQL;
import com.scc.campanha.database.models.RedeSocialDivulgador;
import com.scc.campanha.database.models.pks.PKRedeSocialDivulgador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RedeSocialDivulgadorRepository extends JpaRepository<RedeSocialDivulgador, PKRedeSocialDivulgador> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = ConsultasSQL.INSERIR_REDE_SOCIAL_DIVULGADOR)
    void inserirRedeSocialDivulgador(@Param(value = "NOME_REDE") String nomeRede,
                                                     @Param(value = "NOME_USUARIO") String nomeUsuario,
                                                     @Param(value = "CPF_DIVULGACAO") String cpfDivulgacao,
                                                     @Param(value = "HORAS_VOLUNTARIADO") Integer horasVoluntariado
    );
}
