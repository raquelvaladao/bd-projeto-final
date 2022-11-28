package com.scc.campanha.services;


import com.scc.campanha.database.ConsultasSQL;
import com.scc.campanha.database.models.Motorista;
import com.scc.campanha.database.models.ResultadoColunasSelectResponse;
import com.scc.campanha.database.repositories.MotoristaRepository;
import com.scc.campanha.database.repositories.PublicacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class ConsultarService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Motorista> buscarMembros() {
        //todo: remover
        return motoristaRepository.findAll();
    }

    public List<ResultadoColunasSelectResponse> buscarConteudosPublicadosComMaisCliques(String mesInicio, String mesFim) {
        //equivale ao prepared statement do jdbc. parametriza a consulta pra evitar sql injection.
        Query query = entityManager.createNativeQuery(
                ConsultasSQL.BUSCAR_CONTEUDO_MAIS_CLIQUES_POR_PERIODO
        );

        query.setParameter("MES_INICIO", mesInicio);
        query.setParameter("MES_FIM", mesFim);

        List<Object[]> tuplas = query.getResultList();
        List<ResultadoColunasSelectResponse> response = new ArrayList<>();

        if (tuplas.isEmpty())
            return response;

        tuplas.forEach(tupla ->
                response.add(
                        ResultadoColunasSelectResponse.builder()
                                .titulo(tupla[0].toString())
                                .publicoAlvo(tupla[1].toString())
                                .mediaDeCliques(Float.parseFloat(tupla[2].toString()))
                                .build()
                )
        );
       return response;
    }
}
