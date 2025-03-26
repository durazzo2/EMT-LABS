package mk.ukim.finki.mk.emtlab1.service;

import mk.ukim.finki.mk.emtlab1.model.Accomodation;
import mk.ukim.finki.mk.emtlab1.model.DTOs.BookingDto;

import java.util.List;

public interface AccomodationService {

    List<Accomodation> findAll();

    Accomodation create (BookingDto bookingDto) throws Exception;

    Accomodation update (Long id,BookingDto bookingDto) throws Exception;

    void delete(Long ID);

    Accomodation reservation(Long ID) throws Exception;
}
