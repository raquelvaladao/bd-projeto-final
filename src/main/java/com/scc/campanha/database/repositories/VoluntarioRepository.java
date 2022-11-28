package com.scc.campanha.database.repositories;

import com.scc.campanha.database.ConsultasSQL;
import com.scc.campanha.database.models.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, String> {


   /* @Query(nativeQuery = true, value = ConsultasSQL.INSERIR_VOLUNTARIO)
    Voluntario criar(@Param(value = ":CPF") String cpf,
                     @Param(value = ":NOME") String nome,
                     @Param(value = ":EMAIL") String email,
                     @Param(value = ":TIPO") String tipo,
                     @Param(value = ":CIDADE") String cidade,
                     @Param(value = ":RUA") String rua,
                     @Param(value = ":BAIRRO") String bairro,
                     @Param(value = ":NUMERO") String numero,
                     @Param(value = ":CEP") String cep
    );*/
}
