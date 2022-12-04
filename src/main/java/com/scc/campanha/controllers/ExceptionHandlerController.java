package com.scc.campanha.controllers;

import com.scc.campanha.controllers.dtos.BancoDeDadosException;
import com.scc.campanha.controllers.dtos.MensagemErro;
import com.scc.campanha.database.models.Voluntario;
import com.scc.campanha.enums.TipoVoluntario;
import com.scc.campanha.utils.SQLDicionarioViolacoes;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/*
 *           Classe para tratamento centralizado de exceções. Caso não hajam try/catchs no lançamento de alguma exceção
 *           (não há, em outras partes do código), o Spring procurará a exception lançada dentre essas abaixo.
 *           Ao receber uma exceção, verificamos se a mensagem de erro está no nosso dicionário, mapeamos
 *           e devolvemos uma mensagem apresentável ao usuário por meio da montagem do objeto MensagemErro.
 *
 *           Toda resposta contém o objeto MensagemErro - que contém os erros, uma mensagem apresentável ou mensagem da
 *           exception em si, e o status HTTP web sempre setado como 400 (ou "BAD REQUEST", em que houve algum erro
 *           de violação de constraint ou input malformada pelo usuário).
 *
 *           @ControllerAdvice: anotação do framework para mapeamento dessa classe como a que irá armazenar
 *                              e tratar exceptions (ou seja, como não há try/catchs no código, a aplicação
 *                              buscará aqui por fim)
 *           @ExceptionHandler: anotação do framework Spring para mapear (uma espécie de "try/catch")
 *                              a exception nomeada em questão.
 *          @Slf4j: anotação para logs (usado no método log.info())
 * */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    /*
     *       MissingServletRequestParameterException é a exception lançada quando
     *       falta um parâmetro ou corpo/atributo enviado ao controller.
     * */

    private final SQLDicionarioViolacoes dicionario;

    public ExceptionHandlerController(SQLDicionarioViolacoes dicionario) {
        this.dicionario = dicionario;
    }

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
                                .violacao("Adicione os parâmetros acima para realizar uma ação")
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
                                .violacao(SQLDicionarioViolacoes.gerarMensagemORA(exception.getMessage().replace("\\", "").trim()))
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

//        String violacao = getMensagemException(exception);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(dicionario.gerarMensagemDeErro(exception.getSQLException().getCause().toString()));
    }

    /*
     *       Erros de formatação de consulta SQL.
     * */
    @ExceptionHandler(SQLGrammarException.class)
    public ResponseEntity<Object> handlerSQLGrammarException(
            SQLGrammarException exception) {
        log.info("#handlerSQLGrammarException");

        String mensagemOracle = exception.getSQLException() != null && exception.getSQLException().getCause() != null
                ? exception.getSQLException().getCause().toString()
                : null;

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        MensagemErro.builder()
                                .dataErro(Date.from(Instant.now()))
                                .campos(exception.getSQLException() == null ? null : List.of(exception.getSQLException().toString().trim()))
                                .violacao(SQLDicionarioViolacoes.gerarMensagemORA(mensagemOracle))
                                .build()
                );
    }

    /*
     *       Erros de parseamento de data. Ocorre quando não está na forma 'MMM'
     * */
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<Object> handlerParseException(
            ParseException exception) {
        log.info("#handleParseException");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        MensagemErro.builder()
                                .dataErro(Date.from(Instant.now()))
                                .mensagem("A formatação do mês está inválida. Insira um valor da forma 'MMM'")
                                .violacao(exception.getMessage().replaceAll("\"", ""))
                                .build()
                );
    }

    /*
     *   Handler para envio de input vazio ou nulo.
     * */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.info("#handleHttpMessageNotReadableException");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        MensagemErro.builder()
                                .dataErro(Date.from(Instant.now()))
                                .violacao("Ocorreu erro de formatação/envio de dados.")
                                .mensagem("Verifique se enviou algum parâmetro vazio ou nulo.")
                                .build()
                );
    }

    /*
     *   Exception lançada quando ocorre uma consulta não gerou nenhum resultado.
     * */

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Object> handleNoResultException(
            NoResultException exception) {
        log.info("#handleNoResultException");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        MensagemErro.builder()
                                .dataErro(Date.from(Instant.now()))
                                .mensagem("Não há resultados para essa consulta.")
                                .build()
                );
    }

    //todo: remover
    @ExceptionHandler(BancoDeDadosException.class)
    public ModelAndView handleErros(BancoDeDadosException ex, Model model) {
        ModelAndView view = new ModelAndView(ex.getView());

        if (ex.getMensagem() != null)
            model.addAttribute("erro", ex.getMensagem());

        view.addObject("voluntario", ex.getVoluntarioInvalido() != null ? ex.getVoluntarioInvalido() : new Voluntario());

        view.addObject("tiposVoluntario", Arrays.asList(TipoVoluntario.values()));

        return view;
    }

}
