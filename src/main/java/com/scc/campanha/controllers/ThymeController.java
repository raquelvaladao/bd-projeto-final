package com.scc.campanha.controllers;


import com.scc.campanha.database.models.ResultadoColunasSelectCentro;
import com.scc.campanha.database.models.Voluntario;
import com.scc.campanha.enums.TipoVoluntario;
import com.scc.campanha.services.ConsultarService;
import com.scc.campanha.services.CriarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/base")
@Slf4j
public class ThymeController {

    @Autowired
    private ConsultarService consultarService;

    @Autowired
    private CriarService criarService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("/index.html");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/inserir")
    public ModelAndView mostrarFormularioDeInsercao() {
        ModelAndView view = new ModelAndView("/insert");
        log.info("INDEX -> GET FORMULARIO ##");

        view.addObject("voluntario", new Voluntario());
        view.addObject("tiposVoluntario", Arrays.asList(TipoVoluntario.values()));
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/inserir", consumes = "multipart/form-data")
    public ModelAndView salvarVoluntario(Voluntario voluntario,
                                         BindingResult bind,
                                         RedirectAttributes redirect) throws Exception {
        ModelAndView view = new ModelAndView("/insert");
        ModelAndView redirecionamento = new ModelAndView("redirect:/base/voluntarios");

        Voluntario response = criarService.criarVoluntarioThyme(voluntario);

        redirect.addFlashAttribute("message", "Voluntário inserido com sucesso");
        return redirecionamento;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/voluntarios")
    public ModelAndView selectVoluntarios() throws IOException {
        ModelAndView view = new ModelAndView("/selectVoluntarios"); //html
        List<Voluntario> lista = new ArrayList<>(); //todo: listar voluntarios

        view.addObject("lista", lista);
        return view;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/selectCentros")
    public ModelAndView selectCentros(@RequestParam(value = "inicio", required = false) String mesInicio,
                                      @RequestParam(value = "fim", required = false) String mesFim,
                                      Model model) throws IOException, ParseException {
        ModelAndView view = new ModelAndView("/selectCentros");

        List<ResultadoColunasSelectCentro> lista = consultarService
                .buscarQtdTriagensEArrecadacoesPorCentroThyme(mesInicio, mesFim);

        model.addAttribute("lista", lista);
        return view;
    }

}
