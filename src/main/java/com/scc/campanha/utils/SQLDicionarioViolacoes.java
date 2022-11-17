package com.scc.campanha.utils;

import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class SQLDicionarioViolacoes {

    private Map<String, String> dicionario;

    public SQLDicionarioViolacoes(Map<String, String> dicionario) {
        this.dicionario = dicionario;
    }

    public static String gerarMensagemDeErro(String mensagemException){
        return "";
    }
}
