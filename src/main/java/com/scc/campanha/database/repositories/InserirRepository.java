package com.scc.campanha.database.repositories;


import com.scc.campanha.database.ConsultasSQL;
import com.scc.campanha.database.models.InsercaoVoluntario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;


/*
 *       Essa classe é responsável por pegar os dados do CriarService, que foram enviados
 *       pelo controller, preparar a consulta e enviá-la para o banco. Retorna a resposta
 *       do banco para o controller, caso não hajam exceptions.

 *       A anotação @Repository é anotação interna desse framework para instanciar essa classe
 *       na classe de Service caso necessário
 * */
@Repository
public class InserirRepository {

    private final EntityManagerFactory entityManagerFactory;

    public InserirRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /*
    *   1) Buscamos a sessão com o banco por meio do entityManager.
    *   2) Começamos uma transação
    *   3) Criamos uma consulta parametrizada, protegida contra SQL injection e inserimos os parâmetros
    *   4) Caso não ocorram exceptions, fazemos commit e a conexão é fechada
    *   5) Caso hajam exceptions, fazemos rollback, relançamos a exception para a classe
    *      ExceptionHandlerController a fim de pegar a mensagem e enviá-la para o usuário,
    *      e a conexão é fechada
    * */
    public InsercaoVoluntario inserirVoluntario(InsercaoVoluntario voluntario) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try{
            entityManager.getTransaction().begin();

            Query query = entityManager.createNativeQuery(
                    ConsultasSQL.INSERIR_VOLUNTARIO
            );

            query.setParameter("CPF", voluntario.getCpf());
            query.setParameter("NOME", voluntario.getNome());
            query.setParameter("EMAIL", voluntario.getEmail());
            query.setParameter("TIPO", voluntario.getTipoVoluntario());
            query.setParameter("CIDADE", voluntario.getCidade());
            query.setParameter("RUA", voluntario.getRua());
            query.setParameter("BAIRRO", voluntario.getBairro());
            query.setParameter("NUMERO", voluntario.getNumero());
            query.setParameter("CEP", voluntario.getCep());

            query.executeUpdate();
            entityManager.getTransaction().commit();

            return voluntario;
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
        }
    }
}
