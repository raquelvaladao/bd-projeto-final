package com.scc.campanha.database.repositories;

import com.scc.campanha.database.models.Divulgacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivulgacaoRepository extends JpaRepository<Divulgacao, String> {
}
