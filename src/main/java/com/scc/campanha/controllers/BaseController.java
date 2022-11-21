package com.scc.campanha.controllers;


import com.scc.campanha.controllers.dtos.PublicacaoRequest;
import com.scc.campanha.controllers.dtos.RedeDivulgadorRequest;
import com.scc.campanha.controllers.dtos.RedeSocialRequest;
import com.scc.campanha.database.models.Voluntario;
import com.scc.campanha.services.ConsultaService;
import com.scc.campanha.services.CriarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


/*
*       As anotações @Tag, @Operation e @ApiResponse são da biblioteca OpenApi. São códigos em Java
*       para documentação do endpoint. Estão presentes na interface do sistema, ao iniciar a aplicação, na URL:
*
*       http://localhost:8081/swagger-ui.html. //
*
*       A anotação @Slf4j é apenas para LOG interno.
*       As demais anotações são para controle do Spring, para definir endpoints ou injetar classes (@Autowired).
* */

@RestController
@RequestMapping(path = "/")
@Slf4j
@Tag(
        name = "BaseController",
        description = "Esse controller contém endpoints para executar a consulta de select e insert"
)
public class BaseController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private CriarService criarService;

    @GetMapping("/membros")
    public ResponseEntity<Object> listarMembros() {
        return ResponseEntity.ok(consultaService.buscarMembros());
    }

    @PostMapping("/voluntario")
    @Operation(summary = "Insere um voluntário de algum dado tipo")
    @ApiResponse(responseCode = "400", description = "Erro de violação de constraint ou input malformado")
    @ApiResponse(responseCode = "201", description = "Voluntário foi criado com sucesso")
    @Tag(name = "criarVoluntario")
    public ResponseEntity<Object> criarVoluntario(@RequestBody Voluntario voluntario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(criarService.criar(voluntario));
    }

    @PostMapping("/perfilrede")
    @Operation(summary = "Insere um perfil de rede social")
    @ApiResponse(responseCode = "400", description = "Erro de violação de constraint ou input malformado")
    @ApiResponse(responseCode = "201", description = "Perfil de Rede Social foi criado com sucesso")
    @Tag(name = "criarPerfilRede")
    public ResponseEntity<Object> criarPerfilRede(@RequestBody RedeSocialRequest rede) throws SQLException {
        log.info("RedeReq :: {}", rede);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(criarService.criarRede(rede));
    }

    @PostMapping("/rededivulgador")
    public ResponseEntity<Object> criarRedeDivulgador(@RequestBody RedeDivulgadorRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(criarService.criarRedeDivulgador(request));
    }

    @PostMapping("/publicacao")
    public ResponseEntity<Object> criarPublicacao(@RequestBody PublicacaoRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(criarService.criarPublicacao(request));
    }

}
