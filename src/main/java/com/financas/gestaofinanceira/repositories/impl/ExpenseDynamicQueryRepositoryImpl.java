package com.financas.gestaofinanceira.repositories.impl;

import com.financas.gestaofinanceira.domain.Expense;
import com.financas.gestaofinanceira.domain.Expense_;
import com.financas.gestaofinanceira.repositories.criteria.ExpenseDynamicQueryRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ExpenseDynamicQueryRepositoryImpl implements ExpenseDynamicQueryRepository {

    @Override
    public Specification<Expense> findExpensesByRangeDate(String startDate, String endDate) {
        return ((root, criteriaQuery, cb) -> {
            System.out.println("Entrou na Spec");
            if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
                return cb.between(root.get(Expense_.DATE_OF_PURCHASE).as(String.class), startDate, endDate);
            }
            List<Predicate> predicates = new ArrayList<>();
            if(Objects.nonNull(startDate)){
                predicates.add(cb.greaterThanOrEqualTo(root.get(Expense_.DATE_OF_PURCHASE), startDate));
            }
            if(Objects.nonNull(endDate)){
                predicates.add(cb.lessThanOrEqualTo(root.get(Expense_.DATE_OF_PURCHASE), endDate));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
