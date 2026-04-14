package com.ccsw.tutorial.rental;

import com.ccsw.tutorial.rental.model.Rental;
import com.ccsw.tutorial.rental.model.RentalDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Rental", description = "API of Rental")
@RequestMapping(value = "/rental")
@RestController
@CrossOrigin(origins = "*")
public class RentalController {

    @Autowired
    RentalService rentalService;

    @Autowired
    ModelMapper mapper;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<RentalDto> find(@RequestParam(value = "idGame", required = false) Long idGame, @RequestParam(value = "idClient", required = false) Long idClient, @RequestParam(value = "rentalDate", required = false) Date rentalDate) {

        List<Rental> rentals = rentalService.find(idGame, idClient, rentalDate);

        return rentals.stream().map(e -> mapper.map(e, RentalDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody RentalDto dto) {

        rentalService.save(id, dto);
    }
}
