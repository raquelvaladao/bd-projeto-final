package com.scc.campanha.utils;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
@Component
@ConfigurationProperties(prefix = "dicionario")
public abstract class SQLDicionarioViolacoes {

    /*
        Por padrão o Oracle lança uma mensagem genérica do tipo "ORA-[código]: mensagem".
        Com essa constante de regex utilitária abaixo, pretende-se recuperar essa mensagem para
        erros que não mapeamos no arquivo de dicionário (visto que são muitos, e mapeamos apenas violações
        de constraints).
    */
    public static String REGEX_MENSAGEM_ERRO_ORACLE = "ORA-[0-9]+:";

    /*
        Mapa do tipo "chave: valor" recuperado do arquivo src/main/resources/dicionario.yaml
        contento os mapeamentos de prefixo da constraint violada e a respectiva mensagem genérica
    */
    private Map<String, String> mapaDeViolacoes;

    public static String gerarMensagemDeErro(String mensagemException) {
        //todo: buscar prefixo na chave do mapa e fazer retorno da mensagem
        return "";
    }


    /*
        Recebe uma mensagem do ExceptionHandlerController e retira a mensagem genérica do Oracle
        a partir do regex da constante REGEX_MENSAGEM_ERRO_ORACLE
    */
    public static String gerarMensagemOracleGenerica(String mensagemException) {
        if (Objects.isNull(mensagemException) || Strings.isBlank(mensagemException))
            return null;

        final Matcher stringComRegexEncontrado = Pattern
                .compile(REGEX_MENSAGEM_ERRO_ORACLE)
                .matcher(mensagemException);

        return stringComRegexEncontrado.find()
                ? mensagemException.substring(stringComRegexEncontrado.end()).trim()
                : mensagemException;
    }
}
