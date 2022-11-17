package com.scc.campanha;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class CampanhaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampanhaApplication.class, args);
    }

}
