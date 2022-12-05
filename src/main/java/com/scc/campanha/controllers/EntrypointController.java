package com.scc.campanha.controllers;


import com.scc.campanha.database.models.InsercaoVoluntario;
import com.scc.campanha.services.ConsultarService;
import com.scc.campanha.services.CriarService;
import com.scc.campanha.utils.SQLDicionarioViolacoes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


/*
 *       As anotações @Tag, @Operation e @ApiResponse são da biblioteca OpenApi. São códigos em Java
 *       abstraídos para documentação da interface dessa aplicação. A interface do sistema,
 *       ao iniciar a aplicação, pode ser vista na URL:
 *
 *       http://localhost:8080/swagger-ui.html
 *
 *        Anotações (do tipo @Anotacao) são códigos em java abstraídos. Explicando o que cada uma faz:
 *
 *       A anotação @Slf4j é apenas para logs internos para debug.
 *       A anotação @RequestBody no parâmetro das funções é para receber uma classe como um JSON a partir da interface
 *       A anotação @RequestParam no parâmetro das funções é para receber um parâmetro (string) a partir da interface
 *       As demais anotações, @RestController, @RequestMapping, @GetMapping e @PostMapping
 *       são anotações internas desse framework para definir endpoints (URLs) que estarão disponíveis
 *       na web para enviar dados para essa aplicação, e qual o tipo de operação web (GET ou POST) será feita
 * */
@RestController
@RequestMapping
@Slf4j
public class EntrypointController {

    private final ConsultarService consultaService;
    private final CriarService criarService;

    private final SQLDicionarioViolacoes dicionarioViolacoes;

    public EntrypointController(ConsultarService consultaService, CriarService criarService, SQLDicionarioViolacoes teste) {
        this.consultaService = consultaService;
        this.criarService = criarService;
        this.dicionarioViolacoes = teste;
    }

    @PostMapping("/voluntario")
    @Operation(summary = "Insere um voluntário de um dado tipo")
    @Tag(name = "Criar voluntário (INSERT)")
    public ResponseEntity<Object> criarVoluntario(@RequestBody InsercaoVoluntario voluntario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(criarService.criarVoluntario(voluntario));
    }

    @GetMapping("/roupas")
    @Operation(summary = "Filtrar tipos de roupas enviadas por mês num dado período de meses")
    @Tag(name = "Filtrar tipos de roupas enviadas (SELECT)")
    public ResponseEntity<Object> buscarTipoRoupasEnviadasPorMes(
            @RequestParam(value = "inicio", required = false) String mesInicio,
            @RequestParam(value = "fim", required = false) String mesFim
    ) throws ParseException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(consultaService.buscarTipoRoupasEnviadasPorMes(mesInicio, mesFim));
    }

    @GetMapping("/voluntario/{cpf}")
    @Operation(summary = "Buscar um voluntário a partir do CPF")
    @Tag(name = "Buscar voluntário (SELECT)")
    public ResponseEntity<Object> buscarVoluntario(@PathVariable String cpf) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(consultaService.buscarVoluntario(cpf));
    }

}
