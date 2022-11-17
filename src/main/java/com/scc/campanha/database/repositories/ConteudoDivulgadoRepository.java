package com.scc.campanha.database.repositories;

import com.scc.campanha.database.models.ConteudoDivulgado;
import com.scc.campanha.database.models.pks.PKConteudoDivulgado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConteudoDivulgadoRepository extends JpaRepository<ConteudoDivulgado, PKConteudoDivulgado> {
}
