package com.scc.campanha.database.repositories;

import com.scc.campanha.database.models.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, String> {


    void criar(Voluntario voluntario);
}
