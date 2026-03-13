package com.financas.gestaofinanceira.repositories.impl;

import com.financas.gestaofinanceira.domain.Expense;
import com.financas.gestaofinanceira.domain.Expense_;
import com.financas.gestaofinanceira.domain.User;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ExpenseDynamicQueryRepository {

    public Specification<Expense> findExpensesByRangeDate(String startDate, String endDate, User user) {
        return ((root, criteriaQuery, cb) -> {
            LocalDate startDateToLocalDate;
            LocalDate endDateToLocalDate;
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
                startDateToLocalDate = LocalDate.parse(startDate);
                endDateToLocalDate = LocalDate.parse(endDate);
                predicates.add(cb.between(root.get(Expense_.DATE_OF_PURCHASE), startDateToLocalDate, endDateToLocalDate));
            }
            if(Objects.nonNull(startDate) && Objects.isNull(endDate)){
                startDateToLocalDate = LocalDate.parse(startDate);
                predicates.add(cb.greaterThanOrEqualTo(root.get(Expense_.DATE_OF_PURCHASE), startDateToLocalDate));
            }
            if(Objects.nonNull(endDate) && Objects.isNull(startDate)){
                endDateToLocalDate = LocalDate.parse(endDate);
                predicates.add(cb.lessThanOrEqualTo(root.get(Expense_.DATE_OF_PURCHASE), endDateToLocalDate));
            }
            predicates.add(cb.equal(root.get(Expense_.USER), user));
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
