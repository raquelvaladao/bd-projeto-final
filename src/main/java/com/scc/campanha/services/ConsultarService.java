package com.scc.campanha.services;


import com.scc.campanha.controllers.dtos.MensagemErro;
import com.scc.campanha.database.ConsultasSQL;
import com.scc.campanha.database.models.Motorista;
import com.scc.campanha.database.models.ResultadoColunasSelectResponse;
import com.scc.campanha.database.repositories.MotoristaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class ConsultarService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /*
        Mensagem para ser retornada caso o select não retorne nenhum resultado
    */
    public static final String NENHUM_DADO_ENCONTRADO = "Nenhum dado encontrado para esses parâmetros";

    public List<Motorista> buscarMembros() {
        //todo: remover
        return motoristaRepository.findAll();
    }


    /*
        1) Cria-se com createNativeQuery uma query em SQL puro parametrizada (precavida contra SQL injection)
        2) Setamos os parâmetros inputados pelo usuário vindos da classe de BaseController
        3) O select retorna uma lista de tuplas e, pra cada tupla, mapeamos cada coluna, tupla[n],
           para o respectivo atributo do objeto ResultadoColunasSelectResponse, adicionando-o à lista
        4) Caso o select ocorra com sucesso, mas a resposta venha vazia, retornamos uma mensagem de not_found.
           Se não, retornamos a lista.
    */
    public Object buscarConteudosPublicadosComMaisCliques(String mesInicio, String mesFim) {
        //equivale ao prepared statement do jdbc. parametriza a consulta pra evitar sql injection.
        Query query = entityManager.createNativeQuery(
                ConsultasSQL.BUSCAR_CONTEUDO_MAIS_CLIQUES_POR_PERIODO
        );

        query.setParameter("MES_INICIO", mesInicio);
        query.setParameter("MES_FIM", mesFim);

        List<Object[]> tuplas = query.getResultList();
        List<ResultadoColunasSelectResponse> response = new ArrayList<>();

        tuplas.forEach(tupla ->
                response.add(
                        ResultadoColunasSelectResponse.builder()
                                .titulo(tupla[0].toString())
                                .publicoAlvo(tupla[1].toString())
                                .mediaDeCliques(Float.parseFloat(tupla[2].toString()))
                                .build()
                )
        );

        return !response.isEmpty()
                ? response
                : MensagemErro.builder()
                    .dataErro(Date.from(Instant.now()))
                    .campos(List.of("mesInicio", "mesFim"))
                    .mensagem(NENHUM_DADO_ENCONTRADO)
                .build();
    }
}
