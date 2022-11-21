package com.scc.campanha.controllers;

import com.scc.campanha.controllers.dtos.MensagemErro;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/*
*           Classe para tratamento centralizado de exceções. Caso não hajam try/catchs no lançamento de alguma exceção
*           (não há, em outras partes do código), o Spring procurará a exception lançada dentre essas abaixo.
*           Ao receber uma exceção, verificamos se a mensagem de erro está no nosso dicionário, mapeamos
*           e devolvemos uma mensagem apresentável ao usuário por meio da montagem do objeto MensagemErro.
*
*           Toda resposta contém o objeto MensagemErro, com os erros, uma mensagem apresentável ou mensagem da
*           exception em si, e o status HTTP web sempre setado como 400 (ou "BAD REQUEST", em que houve algum erro
*           de violação de constraint ou input malformada).
* */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {


    /*
    *       Exception quando falta um parâmetro ou corpo/atributo enviado ao controller.
    * */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException exception) {
        log.info("#handleMissingServletRequestParameterException");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        MensagemErro.builder()
                                .dataErro(Date.from(Instant.now()))
                                .campos(Collections.singletonList(exception.getParameterName()))
                                .violacao("Adicione os parâmetros para busca")
                                .build()
                );
    }


    /*
    *   Exception lançada quando ocorre normalmente algum erro do oracle ORA-* do banco, que não seja
    *   de violação de constraint.
    * */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handlerOracleDatabaseException(
            SQLException exception) {
        log.info("#handleSQLException");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        MensagemErro.builder()
                                .dataErro(Date.from(Instant.now()))
                                .violacao(exception.getMessage().replaceAll("\\\\", ""))
                                .build()
                );
    }


    /*
    *       Erros de violação de constraint.
    * */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException exception) {
        log.info("#handleConstraintViolationException");

        String violacao = getMensagemException(exception);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        MensagemErro.builder()
                                .dataErro(Date.from(Instant.now()))
                                .campos(exception.getConstraintName() == null ? null : List.of(exception.getConstraintName()))
                                .violacao(violacao)
                                .build()
                );
    }


    /*
    *       Erros de formatação de consulta SQL.
    * */
    @ExceptionHandler(SQLGrammarException.class)
    public ResponseEntity<Object> handlerSQLGrammarException(
            SQLGrammarException exception) {
        log.info("#handlerSQLGrammarException");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        MensagemErro.builder()
                                .dataErro(Date.from(Instant.now()))
                                .campos(exception.getSQLException() == null ? null : List.of(exception.getSQLException().toString()))
                                .violacao(exception.getSQLException().getCause() == null ? null : exception.getSQLException().getCause().toString())
                                .build()
                );
    }

    /*
     *       Pega mensagem da exception ConstraintViolationException e retira os "\" que existem na mensagem com regex.
     * */
    private String getMensagemException(ConstraintViolationException exception) {
        return exception.getCause() != null
                && exception.getCause().getCause() != null
                && exception.getCause().getCause().getMessage() != null
                ? exception.getCause().getCause().getMessage().replaceAll("\\\\", "")
                : null;
    }

}
