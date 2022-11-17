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

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

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

    private String getMensagemException(ConstraintViolationException exception) {
        return exception.getCause() != null
                && exception.getCause().getCause() != null
                && exception.getCause().getCause().getMessage() != null
                ? exception.getCause().getCause().getMessage().replaceAll("\\\\", "")
                : null;
    }

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

}
