package com.scc.campanha.database.repositories;


import org.springframework.stereotype.Repository;

public interface BaseRepository<Chave, Entidade> {

    Entidade inserir(Entidade entidade);
}
