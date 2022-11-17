package com.scc.campanha.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;


@Component
@ConfigurationProperties(prefix = "dicionario")
public abstract class SQLDicionarioViolacoes {

    @Getter
    @Setter
    private Map<String, String> dados;

    public String gerarMensagemDeErro(String mensagemException){
        //todo: buscar prefixo na chave do mapa e fazer retorno da mensagem
        return "";
    }
}
