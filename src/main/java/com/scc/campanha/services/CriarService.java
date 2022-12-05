package com.scc.campanha.services;


import com.scc.campanha.database.models.InsercaoVoluntario;
import com.scc.campanha.database.repositories.InserirRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/*
 *       Essa classe é responsável por pegar os dados do EntrypointController que foram enviados
 *       pelo usuário e fazer validações de input caso necessário, enviando os dados para o repository
 *       para serem salvos ou recuperados do banco. Retorna a resposta do banco para o controller, caso
 *       não hajam exceptions.

 *       A anotação @Slf4j é apenas para logs internos para debug.
 *       A anotação @Service é anotação interna desse framework para instanciar essa classe
 *       no EntrypointController caso necessário
 * */
@Service
@Slf4j
public class CriarService {

    private InserirRepository inserirRepository;

    public CriarService(InserirRepository inserirRepository) {
        this.inserirRepository = inserirRepository;
    }

    /*
    *   Essa função recebe os dados do EntrypointController e envia para InserirRepository
    *   para que os dados sejam salvos no banco. Retorna a resposta para o controller.
    * */
    public InsercaoVoluntario criarVoluntario(InsercaoVoluntario voluntario) {
        return inserirRepository.inserirVoluntario(voluntario);
    }
}
