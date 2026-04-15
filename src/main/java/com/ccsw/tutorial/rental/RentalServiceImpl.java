package com.ccsw.tutorial.rental;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.rental.model.Rental;
import com.ccsw.tutorial.rental.model.RentalDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RentalServiceImpl implements RentalService {

    @Autowired
    GameService gameService;

    @Autowired
    ClientService clientService;

    @Autowired
    RentalRepository rentalRepository;

    @Override
    public List<Rental> find(Long gameId, Long clientId, Date rentalDate) {

        Specification<Rental> spec = Specification.allOf();

        if (gameId != null) {
            spec = spec.and(new RentalSpecification(new SearchCriteria("game.id", ":", gameId)));
        }
        if (clientId != null) {
            spec = spec.and(new RentalSpecification(new SearchCriteria("client.id", ":", clientId)));
        }
        if (rentalDate != null) {
            spec = spec.and(new RentalSpecification(new SearchCriteria("rentalDate", "<=", rentalDate)));

            Specification<Rental> notReturnedYet = new RentalSpecification(new SearchCriteria("returnDate", ">=", rentalDate));
            Specification<Rental> returnIsNull = new RentalSpecification(new SearchCriteria("returnDate", "isNull", null));

            spec = spec.and(notReturnedYet.or(returnIsNull));
        }

        return this.rentalRepository.findAll(spec);
    }

    @Override
    public void save(Long id, RentalDto dto) {

        Rental rental;

        if (dto.getReturnDate().isBefore(dto.getRentalDate())) {
            throw new RuntimeException();
        }

        if (id == null) {
            rental = new Rental();
        } else {
            rental = this.rentalRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(dto, rental, "id", "game_id", "client_id", "rentalDate", "returnDate");

        rental.setGame(gameService.get(dto.getGame().getId()));
        rental.setClient(clientService.get(dto.getClient().getId()));
        rental.setRentalDate(dto.getRentalDate());
        rental.setReturnDate(dto.getReturnDate());

        this.rentalRepository.save(rental);

    }
}
