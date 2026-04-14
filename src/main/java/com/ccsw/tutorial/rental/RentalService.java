package com.ccsw.tutorial.rental;

import com.ccsw.tutorial.rental.model.Rental;
import com.ccsw.tutorial.rental.model.RentalDto;

import java.util.Date;
import java.util.List;

public interface RentalService {

    List<Rental> find(Long gameId, Long clientId, Date rentalDate);

    void save(Long id, RentalDto dto);

}
