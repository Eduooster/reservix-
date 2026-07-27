package br.com.reservix.infrastructure.persistence.repositories;

import br.com.reservix.core.application.usecases.reservation.ReservationFilter;
import br.com.reservix.infrastructure.persistence.entities.ReservationEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ReservationSpecification {

    public static Specification<ReservationEntity> withFilter(ReservationFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.start() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("start"), filter.start()));
            }

            if (filter.end() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("end"), filter.end()));
            }

            if (filter.roomId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("room").get("id"), filter.roomId()));

            }

            if (filter.userId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("user").get("id"), filter.userId()));

            }

            if (filter.status() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.status()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}