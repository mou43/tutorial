package com.ccsw.tutorial.rental;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.rental.model.Rental;
import com.ccsw.tutorial.rental.model.RentalDto;
import com.ccsw.tutorial.rental.model.RentalSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    public Rental get(Long id) {
        return this.rentalRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Rental> findPage(RentalSearchDto dto) {

        Specification<Rental> spec = Specification.allOf();

        if (dto.getGameId() != null) {
            spec = spec.and(new RentalSpecification(new SearchCriteria("game.id", ":", dto.getGameId())));
        }
        if (dto.getClientId() != null) {
            spec = spec.and(new RentalSpecification(new SearchCriteria("client.id", ":", dto.getClientId())));
        }
        if (dto.getRentalDate() != null) {
            spec = spec.and(new RentalSpecification(new SearchCriteria("rentalDate", "<=", dto.getRentalDate())));

            Specification<Rental> notReturnedYet = new RentalSpecification(new SearchCriteria("returnDate", ">=", dto.getRentalDate()));
            Specification<Rental> returnIsNull = new RentalSpecification(new SearchCriteria("returnDate", "isNull", null));

            spec = spec.and(notReturnedYet.or(returnIsNull));
        }

        Pageable pageable = PageRequest.of(dto.getPageable().getPageNumber(), dto.getPageable().getPageSize());

        return this.rentalRepository.findAll(spec, pageable);
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

        BeanUtils.copyProperties(dto, rental, "id", "game", "client", "rentalDate", "returnDate");

        rental.setGame(gameService.get(dto.getGame().getId()));
        rental.setClient(clientService.get(dto.getClient().getId()));
        rental.setRentalDate(dto.getRentalDate());
        rental.setReturnDate(dto.getReturnDate());

        this.rentalRepository.save(rental);

    }

    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.rentalRepository.deleteById(id);
    }
}
