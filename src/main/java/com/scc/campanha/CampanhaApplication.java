package com.scc.campanha;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Campanha de agasalho - Grupo 5",
        version = "1.0",
        description = "Consultas do grupo 5 referentes à parte 3 do projeto de Banco de Dados (SCC0540)")
)
public class CampanhaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampanhaApplication.class, args);
    }

}
