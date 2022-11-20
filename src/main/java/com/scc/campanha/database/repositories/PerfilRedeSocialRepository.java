package com.scc.campanha.database.repositories;


import com.scc.campanha.database.ConsultasSQL;
import com.scc.campanha.database.models.PerfilRedeSocial;
import com.scc.campanha.database.models.pks.PKPerfilRedeSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;

/*
@Repository
public interface PerfilRedeSocialRepository extends BaseRepository<PKPerfilRedeSocial,PerfilRedeSocial> {

//    @Transactional
//    @Modifying
//    @Query(nativeQuery = true, value = ConsultasSQL.INSERIR_PERFIL_REDE_SOCIAL)
//    void inserirPerfil(@Param(value = "NOME_REDE") String nomeRede,
//                       @Param(value = "NOME_USUARIO") String nomeUsuario) throws SQLException;

}
*/
