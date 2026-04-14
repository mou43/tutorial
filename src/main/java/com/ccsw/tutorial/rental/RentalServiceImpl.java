package com.ccsw.tutorial.rental;

import com.ccsw.tutorial.client.ClientRepository;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameRepository;
import com.ccsw.tutorial.rental.model.Rental;
import com.ccsw.tutorial.rental.model.RentalDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RentalServiceImpl implements RentalService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Rental> find(Long gameId, Long clientId, Date rentalDate) {

        RentalSpecification gameSpec = new RentalSpecification(new SearchCriteria("game.id", ":", gameId));
        RentalSpecification clientSpec = new RentalSpecification(new SearchCriteria("client.id", ":", clientId));

        return List.of();
    }

    @Override
    public void save(Long id, RentalDto dto) {

    }
}
