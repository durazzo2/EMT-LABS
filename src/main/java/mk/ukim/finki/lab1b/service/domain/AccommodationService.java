package mk.ukim.finki.lab1b.service.domain;

import mk.ukim.finki.lab1b.model.domain.Accommodation;
import mk.ukim.finki.lab1b.model.domain.Host;
import mk.ukim.finki.lab1b.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> save(Accommodation accommodation);
    Optional<Accommodation> update(Long id,Accommodation accommodationDto);
    void deleteById(Long id);
    Optional<Accommodation> rentRoom(Long id);
    // In AccommodationService.java
    List<Accommodation> searchAccommodations(String name, Category category, Host host, Integer numRooms);
}
