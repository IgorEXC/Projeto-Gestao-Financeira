package com.financas.GestaoFinanceira.repositories;

import com.financas.GestaoFinanceira.domain.Report;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepositoryImpl{

    @PersistenceContext
    EntityManager em;

    public Report findUserCpf(String cpf){
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF must be provided");
        }
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT r FROM Report r WHERE r.user.cpf = :cpf");
        return em.createQuery(jpql.toString(), Report.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
    }

}
