package com.ccsw.tutorial.rental;

import com.ccsw.tutorial.rental.model.Rental;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalRepository extends CrudRepository<Rental, Long>, JpaSpecificationExecutor<Rental> {

    @Override
    @EntityGraph(attributePaths = { "game", "client" })
    List<Rental> findAll(Specification<Rental> spec);
}
