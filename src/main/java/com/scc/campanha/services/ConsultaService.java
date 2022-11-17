package com.scc.campanha.services;


import com.scc.campanha.database.repositories.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    public Object buscarMembros(){
        return motoristaRepository.findAll();
    }
}
