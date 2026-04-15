package com.ccsw.tutorial.rental;

import com.ccsw.tutorial.rental.model.Rental;
import com.ccsw.tutorial.rental.model.RentalDto;
import com.ccsw.tutorial.rental.model.RentalSearchDto;
import org.springframework.data.domain.Page;

public interface RentalService {

    Rental get(Long id);

    Page<Rental> findPage(RentalSearchDto dto);

    void save(Long id, RentalDto dto);

    void delete(Long id) throws Exception;

}
