package com.ccsw.tutorial.rental.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

import java.time.LocalDate;

public class RentalSearchDto {

    private Long game_id;

    private Long client_id;

    private LocalDate rental_date;

    private PageableRequest pageable;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    public Long getGame_id() {
        return game_id;
    }

    public void setGame_id(Long game_id) {
        this.game_id = game_id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public LocalDate getRental_date() {
        return rental_date;
    }

    public void setRental_date(LocalDate rental_date) {
        this.rental_date = rental_date;
    }
}
