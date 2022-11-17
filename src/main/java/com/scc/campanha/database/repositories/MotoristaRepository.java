package com.scc.campanha.database.repositories;

import com.scc.campanha.database.models.Motorista;
import com.scc.campanha.database.models.Veiculo;
import com.scc.campanha.database.models.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, String> {
}
