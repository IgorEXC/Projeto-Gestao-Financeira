package com.financas.gestaofinanceira.configuration;

import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public interface BaseSpecs<T> {

    default <D> Specification<T> byEquals(SingularAttribute<T, D> attribute, D attributeValue) {
        return ((root, query, cb) -> {
            if (!ObjectUtils.isEmpty(attributeValue)) {
                return cb.equal(root.get(attribute), attributeValue);
            }
            if(attributeValue instanceof String && !ObjectUtils.isEmpty(attributeValue)){
                return cb.equal(cb.upper(root.get(attribute).as(String.class)), ((String) attributeValue).toUpperCase());
            }
            return cb.and();
        });
    }

    default <D> Specification<T> byNotEquals(SingularAttribute<T, D> attribute, D attributeValue){
        return ((root, query, cb) -> {
            if (!ObjectUtils.isEmpty(attributeValue)) {
                return cb.notEqual(root.get(attribute), attributeValue);
            }
            if(attributeValue instanceof String && !ObjectUtils.isEmpty(attributeValue)){
                return cb.notEqual(cb.upper(root.get(attribute).as(String.class)), ((String) attributeValue).toUpperCase());
            }
            return cb.and();
        });
    }
}
