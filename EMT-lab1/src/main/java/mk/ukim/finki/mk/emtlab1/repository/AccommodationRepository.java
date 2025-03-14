package mk.ukim.finki.mk.emtlab1.repository;

import mk.ukim.finki.mk.emtlab1.model.Accomodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accomodation,Long> {
    List<Accomodation> findByIsRented(boolean isRented);
}
