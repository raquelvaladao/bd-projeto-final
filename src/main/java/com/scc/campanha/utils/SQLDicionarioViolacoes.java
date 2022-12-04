package com.scc.campanha.utils;

import com.scc.campanha.controllers.dtos.MensagemErro;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
 *       Essa classe trata e cria mensagens específicas para as exceptions de banco lançadas.
 *
 *
 *       A anotação @Component é anotação interna desse framework para instanciar essa classe
 *       no ExceptionHandlerController caso necessário
 *
 *       As anotações @PropertySource e @ConfigurationProperties são para mapeamento do arquivo
 *          resources/dicionario.properties para o atributo do tipo Map "violacoes" abaixo
 * */
@Component
@PropertySource("classpath:dicionario.properties")
@ConfigurationProperties(prefix = "dicionario")
public class SQLDicionarioViolacoes {


    /*
        Por padrão o Oracle lança uma mensagem genérica do tipo "ORA-[código]: mensagem".
        Com essas constantes de regex utilitárias abaixo, pretende-se recuperar essa mensagem para
        erros que não mapeamos no arquivo de dicionário (visto que são muitos, e mapeamos apenas violações
        de constraints).
    */

    public static final String QUALQUER_DIGITO = "[\\d\\w]+";
    public static String REGEX_MENSAGEM_ERRO_ORACLE = "ORA-[0-9]+:";

    /*
        Mapa do tipo "chave: valor" recuperado do arquivo src/main/resources/dicionario.properties
        contento os mapeamentos de prefixo da constraint violada e a respectiva mensagem genérica
    */
    private Map<String, String> violacoes = new HashMap<>();

    public SQLDicionarioViolacoes(Map<String, String> violacoes) {
        this.violacoes = violacoes;
    }

    public SQLDicionarioViolacoes() {
    }

    /*
        Essa função recebe uma mensagem de erro do ExceptionHandlerController e retira a mensagem genérica do Oracle
        a partir do regex da constante REGEX_MENSAGEM_ERRO_ORACLE para torná-la amigável
    */
    public static String gerarMensagemORA(String mensagemException) {
        if (Objects.isNull(mensagemException) || Strings.isBlank(mensagemException))
            return null;

        final Matcher stringComRegexEncontrado = Pattern
                .compile(REGEX_MENSAGEM_ERRO_ORACLE, Pattern.MULTILINE)
                .matcher(mensagemException);

        return stringComRegexEncontrado.find()
                ? mensagemException.substring(stringComRegexEncontrado.end()).trim()
                : mensagemException;
    }

    /*
        Essa função recebe uma mensagemException do ExceptionHandlerController e:
        1) Verifica se a mensagemException está mapeada no arquivo dicionario.properties e traz a violação do mapa
        2) Se não estiver mapeada (violacaoOcorrida == null), retorna a mensagem sem tratamento
        3) Busca o match de regex do nome da constraint na mensagem em constraintMapeada e retorna
    */
    public MensagemErro gerarMensagemDeErro(String mensagemException) {

        var violacaoOcorrida = violacoes.entrySet().stream()
                .filter(violacao -> mensagemException.contains(violacao.getKey()))
                .findFirst()
                .orElse(null);

        if (violacaoOcorrida == null)
            return MensagemErro.builder()
                    .dataErro(Date.from(Instant.now()))
                    .mensagem(mensagemException)
                    .build();

        String REG = violacaoOcorrida.getKey().concat(QUALQUER_DIGITO);

        final Matcher constraintMapeada = Pattern
                .compile(REG, Pattern.MULTILINE)
                .matcher(mensagemException);

        return MensagemErro.builder()
                .dataErro(Date.from(Instant.now()))
                .violacao(constraintMapeada.find() ? constraintMapeada.group() : mensagemException)
                .mensagem(violacaoOcorrida.getValue())
                .build();
    }

    public Map<String, String> getViolacoes() {
        return violacoes;
    }

    public void setViolacoes(Map<String, String> violacoes) {
        this.violacoes = violacoes;
    }
}
