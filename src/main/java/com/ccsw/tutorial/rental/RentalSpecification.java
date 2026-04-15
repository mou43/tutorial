package com.ccsw.tutorial.rental;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.rental.model.Rental;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class RentalSpecification implements Specification<Rental> {

    private static final long serialVersionUID = 1L;

    private final SearchCriteria criteria;

    public RentalSpecification(SearchCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Rental> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getValue() == null)
            return null;

        Path<?> path = getPath(root);

        return switch (criteria.getOperation()) {
            case ":" -> {
                if (path.getJavaType() == String.class) {
                    yield builder.like((Path<String>) path, "%" + criteria.getValue() + "%");
                } else {
                    yield builder.equal(path, criteria.getValue());
                }
            }
            case "<=" -> builder.lessThanOrEqualTo((Path<Comparable>) path, (Comparable) criteria.getValue());
            case ">=" -> builder.greaterThanOrEqualTo((Path<Comparable>) path, (Comparable) criteria.getValue());
            case "isNull" -> path.isNull();
            default -> null;
        };
    }

    private Path<String> getPath(Root<Rental> root) {
        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }
}
