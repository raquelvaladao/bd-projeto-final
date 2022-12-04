package com.scc.campanha.services;


import com.scc.campanha.controllers.dtos.BancoDeDadosException;
import com.scc.campanha.database.models.ResultadoColunasSelectCentro;
import com.scc.campanha.database.models.ResultadoSelectVoluntario;
import com.scc.campanha.database.repositories.ConsultarRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class ConsultarService {

    @Autowired
    private ConsultarRepository consultarRepository;

    public List<ResultadoColunasSelectCentro> buscarQtdTriagensEArrecadacoesPorCentro(
            String inicio, String fim) throws ParseException {

        if ((Objects.isNull(inicio) || Objects.isNull(fim)) || (Strings.isBlank(inicio) || Strings.isBlank(fim))) {
            inicio = "JAN";
            fim = "DEZ";
        }

        inicio = dataParaNumero(inicio);
        fim = dataParaNumero(fim);

        if (Integer.parseInt(inicio) > Integer.parseInt(fim))
            return consultarRepository.buscarQtdTriagensEArrecadacoesPorCentro(fim, inicio);

        return consultarRepository.buscarQtdTriagensEArrecadacoesPorCentro(inicio, fim);
    }

    public List<ResultadoColunasSelectCentro> buscarQtdTriagensEArrecadacoesPorCentroThyme(
            String inicio, String fim) throws ParseException {

        if (inicio == null || fim == null || inicio.isBlank() || fim.isBlank()) {
            return new ArrayList<>();
        }

        try {
            inicio = dataParaNumero(inicio);
            fim = dataParaNumero(fim);

            if (Integer.parseInt(inicio) > Integer.parseInt(fim))
                return consultarRepository.buscarQtdTriagensEArrecadacoesPorCentro(fim, inicio);

            return consultarRepository.buscarQtdTriagensEArrecadacoesPorCentro(inicio, fim);
        } catch (ParseException e) {
            log.info("#ParseException");
            throw BancoDeDadosException.builder()
                    .view("/selectCentros")
                    .mensagem("A formatação do mês está inválida. Insira um valor da forma 'MMM'")
                    .build();
        }
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
