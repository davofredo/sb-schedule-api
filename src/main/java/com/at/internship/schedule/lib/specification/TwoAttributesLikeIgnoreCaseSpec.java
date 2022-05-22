package com.at.internship.schedule.lib.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TwoAttributesLikeIgnoreCaseSpec<T> implements Specification<T> {
    private final String propertyOne;
    private final String propertyTwo;
    private final String value;

    public TwoAttributesLikeIgnoreCaseSpec(String propertyOne, String propertyTwo, String value) {
        if(propertyOne == null || propertyOne.isBlank() && propertyTwo == null || propertyTwo.isBlank() )
            throw new IllegalArgumentException("Null property name provided to LikeIgnoreCaseSpec");
        this.propertyOne = propertyOne;
        this.propertyTwo = propertyTwo;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate p1 = criteriaBuilder.and(criteriaBuilder.like(root.get(propertyOne),value.toLowerCase()));
        Predicate p2 = criteriaBuilder.and(criteriaBuilder.like(root.get(propertyTwo),value.toLowerCase()));

        return value == null
            ? criteriaBuilder.conjunction()
            : criteriaBuilder.like(criteriaBuilder.upper(root.get(propertyOne)), value.toUpperCase());
    }
}
