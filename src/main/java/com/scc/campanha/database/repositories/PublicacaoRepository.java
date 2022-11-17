package com.scc.campanha.database.repositories;

import com.scc.campanha.database.ConsultasSQL;
import com.scc.campanha.database.models.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = ConsultasSQL.INSERIR_PUBLICACAO)
    void criarPublicacao(@Param(value = "REDE_USUARIO") String usuario,
                         @Param(value = "REDE_REDE") String rede,
                         @Param(value = "CONTEUDO_DIVULGADO_TITULO") String titulo,
                         @Param(value = "CONTEUDO_DIVULGADO_ANO") String ano,
                         @Param(value = "DATA_HORA") String data,
                         @Param(value = "ALCANCE") String alcance,
                         @Param(value = "TOTAL_CLIQUES") String cliques,
                         @Param(value = "IMPRESSOES") String impressoes
    );
}
