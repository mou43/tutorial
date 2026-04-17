package com.ccsw.tutorial.rental;

import com.ccsw.tutorial.rental.model.Rental;
import com.ccsw.tutorial.rental.model.RentalDto;
import com.ccsw.tutorial.rental.model.RentalSearchDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<RentalDto> findPage(@RequestBody RentalSearchDto dto) {

        Page<Rental> page = this.rentalService.findPage(dto);

        return new PageImpl<>(page.getContent().stream().map(e -> mapper.map(e, RentalDto.class)).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody RentalDto dto) {

        rentalService.save(id, dto);
    }

    @RequestMapping(path = { "/{id}" }, method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id", required = true) Long id) throws Exception {

        rentalService.delete(id);
    }
}
