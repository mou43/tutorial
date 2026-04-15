package com.ccsw.tutorial.rental;

import com.ccsw.tutorial.rental.model.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends CrudRepository<Rental, Long>, JpaSpecificationExecutor<Rental> {

    Page<Rental> findAll(Pageable pageable);
}
