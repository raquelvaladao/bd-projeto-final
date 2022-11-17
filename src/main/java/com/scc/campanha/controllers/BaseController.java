package com.scc.campanha.controllers;


import com.scc.campanha.controllers.dtos.PublicacaoRequest;
import com.scc.campanha.controllers.dtos.RedeDivulgadorRequest;
import com.scc.campanha.controllers.dtos.RedeSocialRequest;
import com.scc.campanha.database.models.Voluntario;
import com.scc.campanha.services.ConsultaService;
import com.scc.campanha.services.CriarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
@Slf4j
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
    public ResponseEntity<Object> criarVoluntario(@RequestBody Voluntario voluntario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(criarService.criar(voluntario));
    }

    @PostMapping("/perfilrede")
    public ResponseEntity<Object> criarPerfilRede(@RequestBody RedeSocialRequest rede) {
        log.info("RedeReq :: {}", rede);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarService.criarRede(rede));
    }

    @PostMapping("/rededivulgador")
    public ResponseEntity<Object> criarRedeDivulgador(@RequestBody RedeDivulgadorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(criarService.criarRedeDivulgador(request));
    }

    @PostMapping("/publicacao")
    public ResponseEntity<Object> criarPublicacao(@RequestBody PublicacaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(criarService.criarPublicacao(request));
    }

}
