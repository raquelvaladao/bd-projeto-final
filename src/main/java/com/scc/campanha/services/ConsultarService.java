package com.scc.campanha.services;


import com.scc.campanha.database.models.ResultadoColunasSelectRoupa;
import com.scc.campanha.database.models.ResultadoSelectVoluntario;
import com.scc.campanha.database.repositories.ConsultarRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
public class ConsultarService {

    private final ConsultarRepository consultarRepository;

    public ConsultarService(ConsultarRepository consultarRepository) {
        this.consultarRepository = consultarRepository;
    }

    //todo: doc
    public List<ResultadoColunasSelectRoupa> buscarTipoRoupasEnviadasPorMes(
            String inicio, String fim) throws ParseException {

        if ((Objects.isNull(inicio) || Objects.isNull(fim)) || (Strings.isBlank(inicio) || Strings.isBlank(fim))) {
            inicio = "JAN";
            fim = "DEZ";
        }

        inicio = dataParaNumero(inicio);
        fim = dataParaNumero(fim);

        if (Integer.parseInt(inicio) > Integer.parseInt(fim))
            return consultarRepository.buscarTipoRoupasEnviadasPorMes(fim, inicio);

        return consultarRepository.buscarTipoRoupasEnviadasPorMes(inicio, fim);
    }

    //Dado um mês no formato MMM, retorna o valor numérico
    public String dataParaNumero(String mes) throws ParseException {
        Date dataTexto = new SimpleDateFormat("MMM", Locale.forLanguageTag("pt-BR")).parse(mes.toUpperCase());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataTexto);

        return String.valueOf(cal.get(Calendar.MONTH));
    }

    public ResultadoSelectVoluntario buscarVoluntario(String cpf) {
        return consultarRepository.buscarVoluntario(cpf);
    }
}
