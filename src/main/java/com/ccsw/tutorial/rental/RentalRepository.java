package com.ccsw.tutorial.rental;

import com.ccsw.tutorial.rental.model.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends CrudRepository<Rental, Long>, JpaSpecificationExecutor<Rental> {

    Page<Rental> findAll(Pageable pageable);

    @Query("SELECT r FROM Rental r WHERE r.game.id = :gameId " + "AND r.rentalDate <= :returnDate " + "AND r.returnDate >= :rentalDate " + "AND (:id IS NULL OR r.id != :id)")
    List<Rental> checkGameAvailability(Long gameId, LocalDate rentalDate, LocalDate returnDate, Long id);

    @Query("SELECT r FROM Rental r WHERE r.client.id = :clientId " + "AND r.rentalDate <= :returnDate " + "AND r.returnDate >= :rentalDate " + "AND (:id IS NULL OR r.id != :id)")
    List<Rental> checkClientAvailabilityForRental(Long clientId, LocalDate rentalDate, LocalDate returnDate, Long id);
}
