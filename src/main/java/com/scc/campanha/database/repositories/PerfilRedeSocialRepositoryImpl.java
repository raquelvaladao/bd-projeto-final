package com.scc.campanha.database.repositories;

import com.scc.campanha.database.ConsultasSQL;
import com.scc.campanha.database.models.PerfilRedeSocial;
import com.scc.campanha.database.models.pks.PKPerfilRedeSocial;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


//diz para o spring que essa classe gerencia recursos em memória
@Repository

//por padrão diz que se não há uma transação, o spring cria uma e dá commit automático. Rollback em exceção.
@Transactional
public class PerfilRedeSocialRepositoryImpl implements BaseRepository<PKPerfilRedeSocial, PerfilRedeSocial> {

    public static final int SUCESSO = 1;

    //pega contexto de conexão com o banco de resources/application.yaml
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PerfilRedeSocial inserir(PerfilRedeSocial entidade) {

        //equivale ao prepared statement do jdbc. parametriza a consulta pra evitar sql injection.
        Query query = entityManager.createNativeQuery(
                ConsultasSQL.INSERIR_PERFIL_REDE_SOCIAL
        );

        query.setParameter("NOME_REDE", entidade.getPkPerfilRedeSocial().getRedeSocial());
        query.setParameter("NOME_USUARIO", entidade.getPkPerfilRedeSocial().getNomeUsuario());

        query.executeUpdate();
        return entidade;
    }

}
