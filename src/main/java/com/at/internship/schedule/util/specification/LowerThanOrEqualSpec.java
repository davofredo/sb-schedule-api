package com.at.internship.schedule.util.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LowerThanOrEqualSpec<T> implements Specification<T> {
    private final String property;
    private final Comparable value;
    public LowerThanOrEqualSpec(String property, Comparable value) {
        if(property == null || property.isBlank()) throw new IllegalArgumentException("Null property name provided to LowerThanOrEqualSpec");
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return value == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.lessThanOrEqualTo(root.get(property), value);
    }
}
