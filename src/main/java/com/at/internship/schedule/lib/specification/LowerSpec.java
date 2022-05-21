package com.at.internship.schedule.lib.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LowerSpec<T> implements Specification<T> {
    private final String property;
    @SuppressWarnings("rawtypes")
    private final Comparable value;
    @SuppressWarnings("rawtypes")
    public LowerSpec(String property, Comparable value) {
        if(property == null || property.isBlank()) throw new IllegalArgumentException("Null property name provided to EqualSpec");
        this.property = property;
        this.value = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return value == null
            ? criteriaBuilder.conjunction()
            : criteriaBuilder.lessThan(root.get(property), value);
    }
}