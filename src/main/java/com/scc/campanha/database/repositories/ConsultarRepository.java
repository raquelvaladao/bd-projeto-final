package com.scc.campanha.database.repositories;


import com.scc.campanha.database.ConsultasSQL;
import com.scc.campanha.database.models.InsercaoVoluntario;
import com.scc.campanha.database.models.ResultadoColunasSelectCentro;
import com.scc.campanha.database.models.ResultadoSelectVoluntario;
import com.scc.campanha.enums.TipoVoluntario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;



/*
 *       Essa classe é responsável por pegar os dados do Service que foram enviados
 *       pelo controller, preparar a consulta e enviá-la para o banco. Retorna a resposta
 *       do banco para o controller, caso não hajam exceptions.

 *       A anotação @Repository é anotação interna desse framework para instanciar essa classe
 *       na classe de Service caso necessário
 * */
@Repository
@SuppressWarnings("unchecked")
public class ConsultarRepository {


    /*
    *       Classe para obter sessão de conexão com o banco.
    * */
    private final EntityManagerFactory entityManagerFactory;

    public ConsultarRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<ResultadoColunasSelectCentro> buscarQtdTriagensEArrecadacoesPorCentro(String mesInicio, String mesFim) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNativeQuery(
                ConsultasSQL.BUSCAR_TRIAGENS_DOACOES_DE_CENTROS
        );

        query.setParameter("MES_INICIO", mesInicio);
        query.setParameter("MES_FIM", mesFim);

        List<Object[]> tuplas = query.getResultList();
        List<ResultadoColunasSelectCentro> response = new ArrayList<>();

        tuplas.forEach(tupla ->
                response.add(
                        ResultadoColunasSelectCentro.builder()
                                .rua(tupla[0].toString())
                                .bairro(tupla[1].toString())
                                .numero(tupla[2].toString())
                                .totalTriagens(Integer.parseInt(tupla[3].toString()))
                                .totalArrecadacoes(Integer.parseInt(tupla[4].toString()))
                                .tipo(tupla[5].toString())
                                .build()
                )
        );

        return response;
    }

    public ResultadoSelectVoluntario buscarVoluntario(String cpf) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNativeQuery(
                ConsultasSQL.BUSCAR_VOLUNTARIO_POR_CPF
        );

        query.setParameter("CPF", cpf);

        Object[] tupla = (Object[]) query.getSingleResult();

        return ResultadoSelectVoluntario.builder()
                .cpf(tupla[0].toString())
                .nome(tupla[1].toString())
                .email(tupla[2].toString())
                .tipo(TipoVoluntario.valueOf(tupla[3].toString()).getNome())
                .build();
    }
}
